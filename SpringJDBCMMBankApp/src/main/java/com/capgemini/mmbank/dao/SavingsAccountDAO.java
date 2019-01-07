package com.capgemini.mmbank.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.mmbank.account.SavingsAccount;
import com.capgemini.mmbank.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	boolean deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException, AccountNotFoundException;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	double getAccountBalance(int accountNumber) throws SQLException, AccountNotFoundException, ClassNotFoundException;
	SavingsAccount getAccountByName(String accountHolderName) throws AccountNotFoundException, SQLException, ClassNotFoundException;
	SavingsAccount updateAccountInfo(SavingsAccount savingsAccount) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<SavingsAccount> getAccountByBalRange(double minimumBalance,
			double maxBalance) throws ClassNotFoundException, SQLException;
	
	
}
