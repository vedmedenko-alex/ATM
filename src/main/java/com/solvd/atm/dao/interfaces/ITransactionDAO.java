package com.solvd.atm.dao.interfaces;

import java.util.List;

import com.solvd.atm.models.Transaction;

public interface ITransactionDAO extends IGenericDAO<Transaction> {

    List<Transaction> findLastTransactions(int accountId, int limit) throws Exception;
}
