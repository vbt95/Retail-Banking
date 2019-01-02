package com.Service;

import java.util.ArrayList;
import com.Bean.Account;
import com.Bean.AccountStatus;
import com.Dao.AccountDao;

public class AccountService {

AccountDao dao = new AccountDao();
	
	public int insertAccount(Account a){
		
		int count = dao.addAccount(a);
		
		return count;
		
		
	}
	public Account retrieveAccount(int id){
		
		Account a = new Account();
		a = dao.viewAccount(id);
		
		return a;
		
		
	}
	public int deleteAcc(int accid)
	{
		int count = dao.deleteAccount(accid);
		return count;
	}
	public ArrayList<AccountStatus> viewAllStatus(){
		AccountDao dao=new AccountDao();
		return dao.viewAllStatus();
	}

}
