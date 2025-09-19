package com.solvd.atm.dao.interfaces;

import java.util.List;

import com.solvd.atm.models.Account;

public interface IAccountDAO extends IGenericDAO<Account, Integer> {

    List<Account> findByUserId(int userId) throws Exception;

    void updateBalance(int accountId, double newBalance) throws Exception;
}
