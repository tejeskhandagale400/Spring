package com.capgemini.mmbank.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.mmbank.account.SavingsAccount;
import com.capgemini.mmbank.dao.SavingsAccountDAO;
import com.capgemini.mmbank.dao.SavingsAccountDAOImpl;
import com.capgemini.mmbank.exception.AccountNotFoundException;
import com.capgemini.mmbank.exception.InsufficientFundsException;
import com.capgemini.mmbank.exception.InvalidInputException;
import com.capgemini.mmbank.factory.AccountFactory;
import com.capgemini.mmbank.util.DBUtil;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;
	
	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
 	}

	 
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		account= savingsAccountDAO.createNewAccount(account);
		/*
		 * account=new
		 * SavingsAccount(accountId,account.getBankAccount().getAccountHolderName(),
		 * account.getBankAccount().getAccountBalance(),account.isSalary());
		 */
		return account;
	}

	 
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	 
	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		}else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}
	 
	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		} else {
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}
	}

	 
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		try {
			withdraw(sender, amount);
			deposit(receiver, amount);
			DBUtil.commit();
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
			DBUtil.rollback();
		} catch(Exception e) {
			e.printStackTrace();
			DBUtil.rollback();
		}
	}

	

	 
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	 
	public boolean deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		 
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	 
	public double getAccountBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
 		return savingsAccountDAO.getAccountBalance(accountNumber);
	}

	 
	public SavingsAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, AccountNotFoundException, SQLException {

		return savingsAccountDAO.getAccountByName(accountHolderName);
 
	}

	

	 
	public List<SavingsAccount> sortAllAccount(int option, int sortBy) throws ClassNotFoundException, SQLException {
		List<SavingsAccount> inputAccountList = new ArrayList<SavingsAccount>();
		inputAccountList = getAllSavingsAccount();
		switch(option)
		{
		case 1:sortAccountsByAccountNumber(inputAccountList,sortBy);
	 			break;
		case 2:sortAccountsByAccountHolderName(inputAccountList,sortBy);
			break;
		case 3:sortAccountsByAccountBalance(inputAccountList,sortBy);
		break;
		}
		return inputAccountList;
	}
	
	

	private List<SavingsAccount> sortAccountsByAccountBalance(List<SavingsAccount> inputAccountList, int sortBy) {
		if(sortBy == 1)
		{
			Collections.sort(inputAccountList, new Comparator<SavingsAccount>()
				{
 					public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
					if(accountOne.getBankAccount().getAccountBalance() < accountTwo.getBankAccount().getAccountBalance())
						return -1;
					else if(accountOne.getBankAccount().getAccountBalance() == accountTwo.getBankAccount().getAccountBalance())
						return 0;
					else
					return  1;
					}
			
				}
			);
			return inputAccountList;
		}
		else if(sortBy == 2)
		{
			Collections.sort(inputAccountList, new Comparator<SavingsAccount>()
		 	{

						@Override
						public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
							if(accountOne.getBankAccount().getAccountBalance() < accountTwo.getBankAccount().getAccountBalance())
								return 1;
							else if(accountOne.getBankAccount().getAccountBalance() == accountTwo.getBankAccount().getAccountBalance())
								return 0;
							else
							return  -1;
						}
					 
				
		 	});
		}
		return inputAccountList;
		
	}

	public List<SavingsAccount> sortAccountsByAccountNumber( List<SavingsAccount> inputAccountList ,int sortBy )
	{
		if(sortBy == 1)
		{
			Collections.sort(inputAccountList, new Comparator<SavingsAccount>()
				{
		 
					public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
					if(accountOne.getBankAccount().getAccountNumber() < accountTwo.getBankAccount().getAccountNumber())
						return -1;
					else if(accountOne.getBankAccount().getAccountNumber() == accountTwo.getBankAccount().getAccountNumber())
						return 0;
					else
					return  1;
					}
			
				}
			);
			return inputAccountList;
		}
		else if(sortBy == 2)
		{
			Collections.sort(inputAccountList, new Comparator<SavingsAccount>()
		 	{

 						public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
							if(accountOne.getBankAccount().getAccountNumber() < accountTwo.getBankAccount().getAccountNumber())
								return 1;
							else if(accountOne.getBankAccount().getAccountNumber() == accountTwo.getBankAccount().getAccountNumber())
								return 0;
							else
							return  -1;
						}
					 
				
		 	});
		}
		return inputAccountList;
	}
	
	private List<SavingsAccount> sortAccountsByAccountHolderName(List<SavingsAccount> inputAccountList, int sortBy) {
		if(sortBy == 1)
		{
			Collections.sort(inputAccountList, new Comparator<SavingsAccount>()
				{
 					public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
					return  accountOne.getBankAccount().getAccountHolderName().compareToIgnoreCase(accountTwo.getBankAccount().getAccountHolderName());
					}
			
				}
			);
			return inputAccountList;
		}
		else if(sortBy == 2)
		{
			Collections.sort(inputAccountList, new Comparator<SavingsAccount>()
			{
				 
				public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
				return  -accountOne.getBankAccount().getAccountHolderName().compareToIgnoreCase(accountTwo.getBankAccount().getAccountHolderName());
				}
			});
		}
		return inputAccountList;
	}

 	public SavingsAccount updateAccountInfo(SavingsAccount savingsAccount) throws ClassNotFoundException, SQLException, AccountNotFoundException {

		return savingsAccountDAO.updateAccountInfo(savingsAccount);
	}

 	public List<SavingsAccount> getAccountByBalRange(double minimumBalance,
			double maxBalance) throws ClassNotFoundException, SQLException {
		
		return savingsAccountDAO.getAccountByBalRange( minimumBalance, maxBalance);
	}

	
}
