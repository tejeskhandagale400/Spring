package com.capgemini.mmbank.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.mmbank.account.SavingsAccount;
import com.capgemini.mmbank.exception.AccountNotFoundException;


public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) throws ClassNotFoundException, SQLException;

	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	boolean deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;

	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) throws ClassNotFoundException, SQLException;
	void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;
	void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;
	
	double getAccountBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	SavingsAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, AccountNotFoundException, SQLException;
	
	List<SavingsAccount> sortAllAccount(int option, int sortBy) throws ClassNotFoundException, SQLException ;

	SavingsAccount updateAccountInfo(SavingsAccount savingsAccount) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	List<SavingsAccount> getAccountByBalRange(double minimumBalance,
			double maxBalance) throws ClassNotFoundException, SQLException;
}











