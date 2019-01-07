package com.capgemini.mmbank.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.mmbank.account.SavingsAccount;
import com.capgemini.mmbank.exception.AccountNotFoundException;
import com.capgemini.mmbank.mapper.BankAccountMapper;

@Repository
@Primary
public class SavingAccountSJDAOImpl implements SavingsAccountDAO {

	private Logger logger = Logger.getLogger(SavingAccountSJDAOImpl.class.getName());
	@Autowired
	private JdbcTemplate tempaTemplate;

	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		logger.info("In jdbc  DaoIMP ");
		tempaTemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(),
						account.isSalary(), null, "SA" });
		return account;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount savingsAccount = tempaTemplate.queryForObject("SELECT * FROM account where account_number=?",
				new Object[] { accountNumber }, new BankAccountMapper());
		return savingsAccount;
	}

	@Override
	public boolean deleteAccount(int accountNumber)
			throws SQLException, ClassNotFoundException, AccountNotFoundException {
		tempaTemplate.update("DELETE From account  WHERE account_number = ?", accountNumber);

		return true;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return tempaTemplate.query("SELECT * FROM ACCOUNT", new BankAccountMapper());

	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		tempaTemplate.update("UPDATE ACCOUNT SET account_balance=? where account_number=?", currentBalance, accountNumber);

	}

	@Override
	public double getAccountBalance(int accountNumber)
			throws SQLException, AccountNotFoundException, ClassNotFoundException {
		double accountBalance = getAccountById(accountNumber).getBankAccount().getAccountBalance();
		return accountBalance;
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws AccountNotFoundException, SQLException, ClassNotFoundException {
		System.out.println("in name");
		return tempaTemplate.queryForObject("SELECT * FROM account where account_hn =?",
				new Object[] { accountHolderName }, new BankAccountMapper());
	}

	@Override
	public SavingsAccount updateAccountInfo(SavingsAccount savingsAccount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		tempaTemplate.update("UPDATE account SET account_hn=? , salary=? WHERE account_number=?",
				new Object[] { savingsAccount.getBankAccount().getAccountHolderName(),
						 	savingsAccount.isSalary(),
						 	savingsAccount.getBankAccount().getAccountNumber()});
		return savingsAccount;
	}

	@Override
	public List<SavingsAccount> getAccountByBalRange(double minimumBalance, double maxBalance)
			throws ClassNotFoundException, SQLException {
		return tempaTemplate.query("SELECT * FROM account WHERE account_balance BETWEEN  ? AND ? ",
				new Object[] { minimumBalance, maxBalance }, new BankAccountMapper());
	}

}
