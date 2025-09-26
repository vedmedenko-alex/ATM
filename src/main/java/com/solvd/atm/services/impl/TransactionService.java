package com.solvd.atm.services.impl;

import java.util.List;

import com.solvd.atm.dao.impl.TransactionMyBatisDao;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.services.interfaces.ITransactionService;

public class TransactionService implements ITransactionService {
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

    public void addTransaction(Transaction transaction) throws Exception {
        transactionDAO.insert(transaction);
    }

    /**
     * Returns the last N transactions for an account.
     */
    public List<Transaction> getLastTransactions(int accountId, int limit) {
        return transactionDAO.findLastTransactions(accountId, limit);
    }
}
