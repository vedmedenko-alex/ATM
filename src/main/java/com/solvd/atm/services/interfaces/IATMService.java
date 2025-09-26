package com.solvd.atm.services.interfaces;

import java.util.List;

import com.solvd.atm.models.Account;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.models.User;

public interface IATMService {

    public User login(String cardNumber, String pin) throws Exception;

    public List<Account> checkBalance(int userId) throws Exception;

    public boolean withdraw(int accountId, double amount) throws Exception;

    public boolean deposit(int accountId, double amount) throws Exception;

    public boolean payBill(int accountId, int billId) throws Exception;

    public List<Transaction> getLastTransactions(int accountId, int limit) throws Exception;

}
