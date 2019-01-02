package com.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import oracle.sql.TIMESTAMP;
import java.util.Date;

import com.Bean.Account;
import com.Bean.Transaction;
import com.Controller.LoginServlet;
import com.Util.DbTransaction;

import org.apache.log4j.Logger;

public class TransactionDao{
	
	static final Logger LOGGER = Logger.getLogger(TransactionDao.class);
	public ArrayList deposit(Account account,double amount){
		Connection con=null;
		ArrayList ret=new ArrayList();
		int result=0;
		try{
			System.out.println(account.getAccid());
			con=DbTransaction.getConnection();
			PreparedStatement pst1=con.prepareStatement("SELECT account_balance from account_details_alpha where account_id=?");
			pst1.setInt(1,account.getAccid());
			ResultSet rs=pst1.executeQuery();
			double balance=-1;
			while(rs.next()){
				System.out.println(rs.getDouble("account_balance"));
				balance=rs.getDouble(1);
			}
			
			System.out.println("balance is "+balance);
			if(balance==-1){
				ret.add(0);
				return ret;
			}
			
			double newBalance=balance+amount;
			account.setBalance(balance);
			PreparedStatement pst2=con.prepareStatement("UPDATE account_details_alpha SET account_balance="+
					"? WHERE account_id=?");
			pst2.setDouble(1,newBalance);
			pst2.setInt(2,account.getAccid());
			result=pst2.executeUpdate();
			if(result!=1){
				ret.add(result);
				return ret;
			}
			else{
				PreparedStatement pst3=con.prepareStatement("INSERT INTO transaction_alpha VALUES(seq_transId_alpha.nextval" +
						",?,CURRENT_DATE,'Deposited "+amount+"','credit',?)");
				pst3.setInt(1,account.getAccid());
				pst3.setDouble(2,newBalance);
				result=pst3.executeUpdate();
				ret.add(result);
				ret.add(account.getAccid());
				ret.add(balance);
				ret.add(newBalance);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			ret.add(0);
			return ret;
		}
		finally{
			DbTransaction.closeConnection(con);
		}
		return ret;
}
	
	public ArrayList withdraw(Account account,double amount){
    Connection con=null;
    int result=0;
    ArrayList ret=new ArrayList();
    try{
        con=DbTransaction.getConnection();
        
        PreparedStatement pst1=con.prepareStatement("SELECT account_balance from account_details_alpha where " +
				"account_id=?");
        pst1.setInt(1, account.getAccid());
		ResultSet rs=pst1.executeQuery();
		double balance=-1;
		while(rs.next()){
			balance=rs.getDouble(1);
		}
		
		if(balance==-1){
			ret.add(0);
			return ret;
		}
        
		if(balance<amount){
			ret.add(-1);
			return  ret;
		}
        
		double newBalance=balance-amount;
        PreparedStatement pst2=con.prepareStatement("UPDATE account_details_alpha SET account_balance=? WHERE account_id=?");
        pst2.setDouble(1,newBalance);
		pst2.setInt(2,account.getAccid());
        result=pst2.executeUpdate();
        
        if(result!=1){
        	ret.add(result);
			return ret;
        }
		else{
			PreparedStatement pst3=con.prepareStatement("INSERT INTO transaction_alpha VALUES(seq_transId_alpha.nextval" +
					",?,CURRENT_DATE,'Withdrawal of "+amount+"','debit',?)");
			pst3.setInt(1,account.getAccid());
			pst3.setDouble(2,newBalance);
			result=pst3.executeUpdate();
			ret.add(result);
			ret.add(account.getAccid());
			ret.add(balance);
			ret.add(newBalance);
		}
    }
    catch(Exception e){
        e.printStackTrace();
        ret.add(0);
        return ret;
    }
    finally{
    	DbTransaction.closeConnection(con);
    }
    return ret;
	}

	public ArrayList transfer(int amount,int source,int target){
		Connection con=null;
	    int result=0;
	    ArrayList ret=new ArrayList();
	    try{
	        con=DbTransaction.getConnection();
	        
	        //check whether source account exists
	        PreparedStatement ps1=con.prepareStatement("SELECT account_id,account_balance FROM " +
	        		"account_details_alpha WHERE account_id=?");
	        ps1.setInt(1, source);
	        boolean sourceExists=false;
	        double sourceBalance=-1;
	        ResultSet rs1=ps1.executeQuery();
	        while(rs1.next()){
	        	sourceExists=true;
	        	sourceBalance=rs1.getDouble("account_balance");
	        }
	        if(!sourceExists){
	        	ret.add("Source account not found");
	        	return ret;
	        }
	        
	        //check whether target account existst
	        PreparedStatement ps2=con.prepareStatement("SELECT account_id,account_balance FROM " +
	        		"account_details_alpha WHERE account_id=?");
	        ps2.setInt(1, target);
	        boolean targetExists=ps2.execute();
	        double targetBalance=-1;
	        ResultSet rs2=ps2.executeQuery();
	        while(rs2.next()){
	        	targetExists=true;
	        	targetBalance=rs2.getDouble("account_balance");
	        }
	        if(!targetExists){
	        	ret.add("Target account not found");
	        	return ret;
	        }
	        
	        // if available balance is less than amount specified
	        if(sourceBalance<amount){
	        	ret.add("Transfer not allowed, please choose smaller amount");
	        	return ret;
	        }
	        
	        //source account balance updated
	        double newSourceBalance=sourceBalance-amount;
	        PreparedStatement ps3=con.prepareStatement("UPDATE account_details_alpha SET account_balance="+
					"? WHERE account_id=?");
	        ps3.setDouble(1, newSourceBalance);
	        ps3.setInt(2, source);
	        
	        int sourceUpdate=ps3.executeUpdate();
	        if(sourceUpdate==0){
	        	ret.add("Internal error");
	        	return ret;
	        }
	        //target account balance updated
	        double newTargetBalance=targetBalance+amount;
	        
	        PreparedStatement ps4=con.prepareStatement("UPDATE account_details_alpha SET account_balance="+
					"? WHERE account_id=?");
	        ps4.setDouble(1, newTargetBalance);
	        ps4.setInt(2, target);
	        
	        int targetUpdate=ps4.executeUpdate();
	        if(targetUpdate==0){
	        	ret.add("Internal error");
	        	return ret;
	        }
	        //insert transaction for source account
	        PreparedStatement ps5=con.prepareStatement("INSERT INTO transaction_alpha VALUES(seq_transId_alpha.nextval" +
					",?,CURRENT_DATE,'Transfer of "+amount+" to "+target+"','debit',?)");
	        ps5.setInt(1,source);
			ps5.setDouble(2,amount);
			int sourceTransactionstatus=ps5.executeUpdate();
			
			PreparedStatement ps6=con.prepareStatement("INSERT INTO transaction_alpha VALUES(seq_transId_alpha.nextval" +
					",?,CURRENT_DATE,'Transfer of "+amount+" from "+source+"','credit',?)");
	        ps6.setInt(1,target);
			ps6.setDouble(2,amount);
			
			int targetTransactionstatus=ps6.executeUpdate();
	        
	        ret.add("Amount transfer completed successfully");
	        ret.add(source);
	        ret.add(sourceBalance);
	        ret.add(newSourceBalance);
	        
	        ret.add(target);
	        ret.add(targetBalance);
	        ret.add(newTargetBalance);
	    }
	    catch(Exception e){
	        	ret.add("Internal error");
	        	return ret;
	    }
	    finally{
	    	DbTransaction.closeConnection(con);
	    }
	    
	    return ret;
	}
	
	public ArrayList<Transaction> viewStatement(int accountId,int days){
		ArrayList<Transaction> result=new ArrayList<Transaction>();
		Connection con=null;
		try{
			con=DbTransaction.getConnection();
			PreparedStatement pst=con.prepareStatement("SELECT * FROM (SELECT * FROM transaction_alpha"+
					" WHERE account_id=? ORDER BY tdate DESC) WHERE rownum<=?"
					);
			pst.setInt(1,accountId);
			pst.setInt(2,days);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				String id=rs.getString("transaction_id");
				int acctId=rs.getInt("account_id");
				Timestamp date=rs.getTimestamp("tdate");
				String description=rs.getString("description");
				String type=rs.getString("trans_type");
				double balance=rs.getDouble("balance");
				Transaction t=new Transaction(id, acctId, date, description, type, balance);
				result.add(t);
			}
			LOGGER.info("Successfully viewed statement of "+accountId);
		}
		catch(Exception e){
			LOGGER.info(e.getStackTrace());
			
		}
		
		return result;
	}

	public ArrayList<Transaction> viewStatement(int accountId,Date startDate,Date endDate){
		ArrayList<Transaction> result=new ArrayList<Transaction>();
		Connection con=null;
		try{
			con=DbTransaction.getConnection();
			PreparedStatement pst=con.prepareStatement("SELECT * FROM transaction_alpha"+
					" WHERE account_id=? AND tdate BETWEEN ? AND ?");
			java.sql.Timestamp start = new Timestamp(startDate.getTime());
			java.sql.Timestamp end   = new Timestamp(endDate.getTime());
			pst.setInt(1,accountId);
			pst.setTimestamp(2, start);
			pst.setTimestamp(3,end);
			
			System.out.println(start);
			System.out.println(end);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				String id=rs.getString("transaction_id");
				int acctId=rs.getInt("account_id");
				Timestamp date=rs.getTimestamp("tdate");
				String description=rs.getString("description");
				String type=rs.getString("trans_type");
				double balance=rs.getDouble("balance");
				Transaction t=new Transaction(id, acctId, date, description, type, balance);
				result.add(t);
			}
			LOGGER.info("Successfully viewed statement of "+accountId);
		}
		catch(Exception e){
			LOGGER.info(e.getStackTrace());
			e.printStackTrace();
		}
		
		return result;
	}
}


