package com.Service;

import java.util.ArrayList;
import java.util.Date;
import com.Bean.Account;
import com.Bean.Transaction;
import com.Dao.TransactionDao;

public class TransactionService {
	TransactionDao dao=new TransactionDao();
	public ArrayList deposit(Account account,double amount){
		
		return dao.deposit(account, amount);
	}
	public ArrayList withdraw(Account account,double amount){

		return dao.withdraw(account, amount);
	}
	public ArrayList transfer(int amount,int source,int target){
		
		return dao.transfer(amount,source,target);
	}
	public ArrayList<Transaction> getStatement(int accountId,int days){
		return dao.viewStatement(accountId, days);
	}
	
	public ArrayList<Transaction> getStatement(int accountId,Date start,Date end){
		return dao.viewStatement(accountId, start,end);
	}
}
