package com.solvd.atm.services.impl;

import com.solvd.atm.dao.impl.TransactionMyBatisDao;
import com.solvd.atm.models.Transaction;
import java.util.List;

public class TransactionService {
    private final TransactionMyBatisDao transactionDAO;

    public TransactionService(TransactionMyBatisDao transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    /**
     * Returns all transactions in the system.
     */
    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }

    /**
     * Returns the last N transactions for an account.
     */
    public List<Transaction> getLastTransactions(int accountId, int limit) {
        return transactionDAO.findLastTransactions(accountId, limit);
    }
}
