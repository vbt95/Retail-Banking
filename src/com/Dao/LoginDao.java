package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.Bean.Employee;
//import com.DbConnection;
import com.Util.DbTransaction;

public class LoginDao {
	public int authenticateUser(Employee emp)
	{
		int status=0;
		Connection con=null;
		try{
			con=DbTransaction.getConnection();
			PreparedStatement ps=con.prepareStatement("SELECT emp_type from USERSTORE_ALPHA "+
			"WHERE user_id=? AND password=?");
			
			ps.setString(1, emp.getUser_id());
			ps.setString(2, emp.getPassword());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String type=rs.getString(1);
				if(type.equals("Customer-Account Executive"))
					status=1;
				else if(type.equals("Cashier")|| type.equals("Teller"))
					status=2;
			}
		}
		catch(Exception e){
			status=-1;
			DbTransaction.closeConnection(con);
			e.printStackTrace();
		}
		finally{
			DbTransaction.closeConnection(con);
		}
		return status;

	}
	
	public void updateTimestamp(Employee emp){
		
		Connection con=null;
		try{
			con=DbTransaction.getConnection();
			PreparedStatement pst=con.prepareStatement("UPDATE userstore_alpha SET time_stamp=CURRENT_TIMESTAMP " +
					"WHERE user_id=?");
			pst.setString(1, emp.getUser_id());
			int status=pst.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DbTransaction.closeConnection(con);
		}
	}
}