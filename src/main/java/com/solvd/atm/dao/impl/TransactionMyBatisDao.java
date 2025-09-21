package com.solvd.atm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.interfaces.ITransactionDAO;

import com.solvd.atm.models.Transaction;

public class TransactionMyBatisDao implements ITransactionDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public TransactionMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Transaction transaction) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.atm.mappers.TransactionMapper.insert", transaction);
        }
    }

    @Override
    public Transaction getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.atm.mappers.TransactionMapper.findById", id);
        }
    }

    @Override
    public List<Transaction> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.atm.mappers.TransactionMapper.findAll");
        }
    }

    @Override
    public void update(Transaction transaction) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.atm.mappers.TransactionMapper.update", transaction);
        }
        
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.atm.mappers.TransactionMapper.delete", id);
        }
    }

    @Override
    public List<Transaction> findLastTransactions(int accountId, int limit) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            java.util.Map<String, Object> params = new java.util.HashMap<>();
            params.put("accountId", accountId);
            params.put("limit", limit);
            return session.selectList("com.solvd.atm.mappers.TransactionMapper.findLastTransactions", params);
        }
    }
}
