package com.Dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.Bean.Customer;
import com.Bean.CustomerStatus;
import com.Util.DbTransaction;
//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class CustomerDao {

public int addNewCustomer(Customer c)
{
    int count = 0;
    Connection con=null;
    try {
            con = DbTransaction.getConnection();

            String query = "insert into customer_details_alpha values(seq_customerId.nextval,?," +
                    "?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, c.getSSN());
            pst.setString(2, c.getCustomerName());
            pst.setInt(3,c.getAge());
            pst.setString(4, c.getAddressLine1());
            pst.setString(5, c.getAddressLine2());
            pst.setString(6, c.getCity());
            pst.setString(7, c.getState());

            count = pst.executeUpdate();

            PreparedStatement pst2=con.prepareStatement("select customer_id from " +
                    "customer_details_alpha where customer_ssn=?");
            pst2.setInt(1,c.getSSN());
            ResultSet rs=pst2.executeQuery();
            int id=0;
            while(rs.next()){
                id=rs.getInt(1);
            }
            if(id==0)
                return 0;
           
            PreparedStatement pst3=con.prepareStatement("insert into customer_status_alpha" +
                    " values(?,?,'initiated','Customer creation initiated successfully'," +
                    "CURRENT_DATE)");
            pst3.setInt(1,id);
            pst3.setInt(2,c.getSSN());

            count=pst3.executeUpdate();

    }
    catch (SQLException e1) {

        e1.printStackTrace();
        return 0;
    }
    catch (Exception e2) {

        e2.printStackTrace();
        return 0;
    }
    finally{
			DbTransaction.closeConnection(con);
		}
    return count;
}
public CustomerStatus searchCustomer(int id){

    CustomerStatus cust = new CustomerStatus();
    Connection con=null;
    try {
            con = DbTransaction.getConnection();
            String query = "select * from CUSTOMER_STATUS_ALPHA a left outer join customer_details_alpha"+
                        " b on a.customer_id=b.customer_id where a.customer_id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                cust.setAddressLine1(rs.getString("address_line1"));
                cust.setAddressLine2(rs.getString("address_line2"));
                cust.setAge(rs.getInt("age"));
                cust.setCity(rs.getString("city"));
                cust.setCustomerID(rs.getInt("customer_id"));
                cust.setCustomerName(rs.getString("name"));
                cust.setSSN(rs.getInt("customer_ssn"));
                cust.setState(rs.getString("state"));
                cust.setStatus(rs.getString("status"));
                cust.setMessage(rs.getString("message"));
                cust.setLastUpdate(rs.getTimestamp("last_update"));
                return cust;
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
    return null;

}

public CustomerStatus searchCustomerSSN(int id){

    CustomerStatus cust = new CustomerStatus();
    Connection con=null;
    try {
            con = DbTransaction.getConnection();

            String query = "select * from CUSTOMER_STATUS_ALPHA a left outer join customer_details_alpha"+
                    " b on a.customer_id=b.customer_id where a.customer_ssn=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                cust.setAddressLine1(rs.getString("address_line1"));
                cust.setAddressLine2(rs.getString("address_line2"));
                cust.setAge(rs.getInt("age"));
                cust.setCity(rs.getString("city"));
                cust.setCustomerID(rs.getInt("customer_id"));
                cust.setCustomerName(rs.getString("name"));
                cust.setSSN(rs.getInt("customer_ssn"));
                cust.setState(rs.getString("state"));
                cust.setStatus(rs.getString("status"));
                cust.setMessage(rs.getString("message"));
                cust.setLastUpdate(rs.getTimestamp("last_update"));

            return cust;
            }
            else
            	return null;
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
    return null;

}
public int UpdateCustomer(CustomerStatus c) throws Exception
{
    int count = 0;
    Connection con=null;
    try {
            con = DbTransaction.getConnection();

            String query = "update customer_details_alpha set name=?,age=?,address_line1=?,"+
                        "address_line2=?,city=?,state=? where customer_id=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, c.getCustomerName());
            pst.setInt(2, c.getAge());
            pst.setString(3,c.getAddressLine1());
            pst.setString(4, c.getAddressLine2());
            pst.setString(5, c.getCity());
            pst.setString(6, c.getState());
            pst.setInt(7, c.getCustomerID());       
            count = pst.executeUpdate();

            // UPDATING CUSTOMER STATUS TABLE
            PreparedStatement pst2=con.prepareStatement("update customer_status_alpha" +
                    " set status=?,message=?," +
                    "last_update=CURRENT_DATE where customer_id=?");
            pst2.setString(1, c.getStatus());
            pst2.setString(2, c.getMessage());
            pst2.setInt(3, c.getCustomerID());
            count=pst2.executeUpdate();
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
    return count;
}

public ArrayList<CustomerStatus> viewAllStatus(){
    ArrayList<CustomerStatus> list=new ArrayList<CustomerStatus>();
    Connection con=null;
    try {
        con = DbTransaction.getConnection();

        PreparedStatement ps=con.prepareStatement("SELECT * FROM customer_status_alpha");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            int id=rs.getInt("customer_id");
            int ssn=rs.getInt("customer_ssn");
            String status=rs.getString("status");
            String message=rs.getString("message");
            Timestamp last_update=rs.getTimestamp("last_update");
            //dateFormat.format(last_update);
            CustomerStatus custStatus=new CustomerStatus(ssn, id, status, message, last_update);

            //System.out.println(last_update);
            list.add(custStatus);
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

public int deleteCustomer(int customerID) {

	int check = 0;
	Connection con=null;
	try{
		con = DbTransaction.getConnection();
		String query = "DELETE FROM account_status_alpha WHERE customer_id=?";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setInt(1,customerID);
		check=statement.executeUpdate();
		
			query = "select account_id FROM account_details_alpha WHERE customer_id=?";
			statement =con.prepareStatement(query);
			statement.setInt(1,customerID);
			ResultSet rs=statement.executeQuery();
			ArrayList<Integer> alist = new ArrayList<>();
			while(rs.next()){
				alist.add(rs.getInt(1));
			}
			for(Integer i : alist){
				query = "delete FROM transaction_alpha WHERE account_id=?";
				statement =con.prepareStatement(query);
				statement.setInt(1,i);
				check=statement.executeUpdate();
			}
		
		
			query = "DELETE FROM customer_status_alpha WHERE customer_id=?";
			statement =con.prepareStatement(query);
			statement.setInt(1,customerID);
			check=statement.executeUpdate();
		
		
			query = "DELETE FROM account_details_alpha WHERE customer_id=?";
			statement =con.prepareStatement(query);
			statement.setInt(1,customerID);
			check=statement.executeUpdate();
	
		
			query = "DELETE FROM customer_details_alpha WHERE customer_id=?";
			statement =con.prepareStatement(query);
			statement.setInt(1,customerID);
			check=statement.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 finally{
			DbTransaction.closeConnection(con);
		}
	
	
	// TODO Auto-generated method stub
	return check;
}

public CustomerStatus updateStatus(int custId){
	CustomerStatus custStatus=null;
	Connection con=null;
    try {
        con = DbTransaction.getConnection();

        PreparedStatement ps=con.prepareStatement("SELECT * FROM customer_status_alpha where customer_id=?");
        ps.setInt(1, custId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            int ssn=rs.getInt("customer_ssn");
            String status=rs.getString("status");
            String message=rs.getString("message");
            Timestamp last_update=rs.getTimestamp("last_update");
            //dateFormat.format(last_update);
            custStatus=new CustomerStatus(ssn, custId, status, message, last_update);
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
        return custStatus;
    }

	
}
}

