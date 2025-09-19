package com.solvd.atm.dao.interfaces;

import java.util.List;

import com.solvd.atm.models.Transaction;

public interface TransactionDAO extends IGenericDAO<Transaction, Integer> {

    List<Transaction> findLastTransactions(int accountId, int limit) throws Exception;
}
