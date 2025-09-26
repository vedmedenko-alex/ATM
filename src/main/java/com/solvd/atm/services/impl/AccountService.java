package com.solvd.atm.services.impl;

import java.util.List;

import com.solvd.atm.dao.interfaces.IAccountDAO;
import com.solvd.atm.models.Account;
import com.solvd.atm.services.interfaces.IAccountService;

public class AccountService implements IAccountService {

    private final IAccountDAO accountDAO;

    public AccountService(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Account> getAccountsByUser(int userId) throws Exception {
        return accountDAO.getByUserId(userId);
    }

    public Account getAccountById(int accountId) throws Exception {
        return accountDAO.getById(accountId);
    }

    public void updateBalance(int accountId, double newBalance) throws Exception {
        accountDAO.updateBalance(accountId, newBalance);
    }
}
