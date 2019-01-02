package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.Bean.Account;
import com.Bean.CustomerStatus;
import com.Util.DbTransaction;
import com.Bean.AccountStatus;

public class AccountDao {
	
	
	
	public int addAccount(Account a){
		Connection con=null;
		int cnt = 0;
		try {

			con = DbTransaction.getConnection();
			String query1="select customer_ssn from customer_details_alpha where customer_id=?";
			PreparedStatement pst1=con.prepareStatement(query1);
			pst1.setInt(1,a.getCustid());
			ResultSet rs=pst1.executeQuery();
			int ssn=0;
			while(rs.next())
				ssn=rs.getInt("customer_ssn");
			if(ssn==0)
				return 0;
			String query = "insert into account_details_alpha values(seq_accId_alpha.nextval,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, a.getCustid());
			pst.setInt(2,ssn);
			pst.setString(3, a.getAcctype());
			pst.setDouble(4, a.getBalance());
			cnt=0;
			cnt = pst.executeUpdate();
			if(cnt==0)
				return 0;
			
			//UPDATING ACCOUNT STATUS TABLE
			

            PreparedStatement pst2=con.prepareStatement("insert into account_status_alpha" +
                    " values(?,seq_accId_alpha.currval,?,'initiated','Account Creation initiated Successfully'," +
                    "CURRENT_DATE)");
            pst2.setInt(1,a.getCustid());
            pst2.setString(2, a.getAcctype());
            cnt=pst2.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			DbTransaction.closeConnection(con);
		}

		return cnt;

	}
	
	public int deleteAccount(int id)

	   {
		   Connection con=null;
		   int cnt = 0;
		   try{
			   con = DbTransaction.getConnection();

			String query = "delete from account_status_alpha where account_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			cnt = pst.executeUpdate();
			//if(cnt>0){
			query = "delete from transaction_alpha where account_id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			cnt = pst.executeUpdate();
			//}
			//if(cnt>0){
				query = "delete from account_details_alpha where account_id=?";
				pst = con.prepareStatement(query);
				pst.setInt(1, id);
				cnt = pst.executeUpdate();
			//}
		   }
		   catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   finally{
				DbTransaction.closeConnection(con);
			}
	         return cnt;
	   }
	   
   
   public ArrayList<AccountStatus> viewAllStatus(){
	    ArrayList<AccountStatus> list=new ArrayList<AccountStatus>();
	    Connection con=null;
	    try {
	        con = DbTransaction.getConnection();

	        PreparedStatement ps=con.prepareStatement("SELECT * FROM account_status_alpha");
	        ResultSet rs=ps.executeQuery();
	        while(rs.next()){
	            int custId=rs.getInt("customer_id");
	            int acctId=rs.getInt("account_id");
	            String acctType=rs.getString("account_type");
	            String status=rs.getString("status");
	            String message=rs.getString("message");
	            Timestamp last_update=rs.getTimestamp("last_update");
	            //dateFormat.format(last_update);
	            AccountStatus acctStatus=new AccountStatus(custId,acctId,acctType, status, message, last_update);

	            //System.out.println(last_update);
	            list.add(acctStatus);
	        }
	    }
	    catch (SQLException e1) {

	        e1.printStackTrace();
	    }
	    catch (Exception e1) {

	        e1.printStackTrace();
	    }
	    finally{
	        DbTransaction.closeConnection(con);
	    }

	    return list;
	}
   
   public ArrayList<Integer> viewAccountId(int id ,String IdType)
   {
   	ArrayList<Integer> idlist=new ArrayList<Integer>();
   	String type=IdType;
   	Connection con=null;
   	//System.out.println("i am in a cust type details");
   	try {
   		if(type.equalsIgnoreCase("SSN ID"))
   		{
   			//System.out.println("i am in a ssn id");
   		  String query = "select account_id from account_details_alpha where customer_ssn=?";
   		  con = DbTransaction.getConnection();
   		  PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,
   		        ResultSet.CONCUR_READ_ONLY);	
   		  pst.setInt(1, id);
   		  ResultSet rs = pst.executeQuery();
   		  if(rs.next()==false)
         	return null;
   		  
   		  rs.first();
   		  idlist.add(rs.getInt(1));
   		 while(rs.next())
   		 {
   		   int ids= rs.getInt(1);
   		   idlist.add(ids);
   		   //System.out.println(ids);
   		  }
   		}
   		else if(type.equalsIgnoreCase("Customer ID"))
   		{
   			
   			String query = "select account_id from account_details_alpha where customer_id=?";
   			 con = DbTransaction.getConnection();
   			  PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,
   	   		        ResultSet.CONCUR_READ_ONLY);	
   			  pst.setInt(1, id);
   	     	  ResultSet rs = pst.executeQuery();
   	     	 if(rs.next()==false)
   	         	return null;
   	   		  
   	   		  
   	     	 	rs.first();
   	     	 	idlist.add(rs.getInt(1));
   	   		 while(rs.next())
   	   		 {
   		 
   			   int ids= rs.getInt(1);
   		     	idlist.add(ids);


   				
   			  }
   		}
   		}
   		 catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} catch (Exception e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		finally{
   			DbTransaction.closeConnection(con);
   		}
   	return idlist;

   	
   	
   }


   public Account viewAccount(int id)
   {	

   	Account acc= new Account();
   	Connection con=null;
   	try {
   		String query = "select customer_id ,account_id,account_type, account_balance from account_details_alpha where account_id=?";
   		con = DbTransaction.getConnection();
   		PreparedStatement pst = con.prepareStatement(query);	
   		pst.setInt(1, id);
       
   		ResultSet rs = pst.executeQuery();
   		if(rs.next()==false)
   				return null;
   		rs = pst.executeQuery();
   		while(rs.next()){
   			
   		int cid= rs.getInt("customer_id");
   		int aid=	rs.getInt("account_id");
   		String atype=rs.getString("account_type");
   	    double bal=rs.getDouble("account_balance");
   	    
   			acc=new Account(aid,cid,atype,bal);
   			
   		}
   		}

   		 catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} catch (Exception e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		finally{
			DbTransaction.closeConnection(con);
		}

   	return acc;
   	
   }
		
   public ArrayList<Account> viewAccountIdTable(int id ,String IdType)
   {
   	ArrayList<Account> idlist=new ArrayList<Account>();
   	String type=IdType;
   	Connection con=null;
   	//System.out.println("i am in a cust type details");
   	try {
   		if(type.equalsIgnoreCase("SSN ID"))
   		{

   		  String query = "select * from account_details_alpha where customer_ssn=?";
   		  con = DbTransaction.getConnection();
   		  PreparedStatement pst = con.prepareStatement(query);	
   		  pst.setInt(1, id);
        	  ResultSet rs = pst.executeQuery();
   		 while(rs.next())
   		 {
   	 
   		   int acctid= rs.getInt("account_id");
   		   String accttype=rs.getString("account_type");
   		   double balance=rs.getDouble("account_balance");
   		   
   		   Account acct=new Account();
   		   
   		   acct.setAccid(acctid);
   		   acct.setAcctype(accttype);
   		   acct.setBalance(balance);
   		   idlist.add(acct);
   		   //System.out.println(ids);
   		  }
   		}
   		else if(type.equalsIgnoreCase("Customer ID"))
   		{

   			String query = "select * from account_details_alpha where customer_id=?";
   			  con = DbTransaction.getConnection();
   			  PreparedStatement pst = con.prepareStatement(query);	
   			  pst.setInt(1, id);
   	     	  ResultSet rs = pst.executeQuery();
   			 while(rs.next())
   			 {
   		 
   				 int acctid= rs.getInt("account_id");
   	  		   	String accttype=rs.getString("account_type");
   	  		   	double balance=rs.getDouble("account_balance");
   	  		   
   	  		   	Account acct=new Account();
   	  		   
   	  		   	acct.setAccid(acctid);
   	  		   	acct.setAcctype(accttype);
   	  		   	acct.setBalance(balance);
   	  		   	idlist.add(acct);

   			  }
   		}
   		}
   		 catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} catch (Exception e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		finally{
   			DbTransaction.closeConnection(con);
   		}
   	return idlist;

   	
   	
   }
	
   public AccountStatus updateStatus(int acctId){
	   Connection con=null;
	   AccountStatus acctStatus=null;
	    try {
	        con = DbTransaction.getConnection();

	        PreparedStatement ps=con.prepareStatement("SELECT * FROM account_status_alpha where account_id=?");
	        ps.setInt(1,acctId);
	        ResultSet rs=ps.executeQuery();
	        
	        while(rs.next()){
	            int custId=rs.getInt("customer_id");
	            //int acctId=rs.getInt("account_id");
	            String acctType=rs.getString("account_type");
	            String status=rs.getString("status");
	            String message=rs.getString("message");
	            Timestamp last_update=rs.getTimestamp("last_update");
	            //dateFormat.format(last_update);
	            acctStatus=new AccountStatus(custId,acctId,acctType, status, message, last_update);

	            //System.out.println(last_update)
	        }
	    }
	    catch (SQLException e1) {

	        e1.printStackTrace();
	    }
	    catch (Exception e1) {

	        e1.printStackTrace();
	    }
	    finally{
	        DbTransaction.closeConnection(con);
	    }

	    return acctStatus;
	}
   
}
