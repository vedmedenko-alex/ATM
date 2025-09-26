package com.solvd.atm.services.interfaces;

import java.util.List;

import com.solvd.atm.models.Transaction;

public interface ITransactionService {

    public List<Transaction> getAllTransactions() throws Exception;

    public void addTransaction(Transaction transaction) throws Exception;

    public List<Transaction> getLastTransactions(int accountId, int limit) throws Exception;

}
