package com.solvd.atm.dao.interfaces;

import java.util.List;

import com.solvd.atm.models.Transaction;

public interface ITransactionDAO extends IGenericDAO<Transaction> {

    /**
     * Returns all transactions in the system.
     */
    List<Transaction> findAll();

    /**
     * Returns the last N transactions for an account.
     */
    List<Transaction> findLastTransactions(int accountId, int limit);
}
