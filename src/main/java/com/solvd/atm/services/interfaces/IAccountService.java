package com.solvd.atm.services.interfaces;

import java.util.List;

import com.solvd.atm.models.Account;

public interface IAccountService {

    public List<Account> getAccountsByUser(int userId) throws Exception;

    public Account getAccountById(int accountId) throws Exception;

    public void updateBalance(int accountId, double newBalance) throws Exception;

}
