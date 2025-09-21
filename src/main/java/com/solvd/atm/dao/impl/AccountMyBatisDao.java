package com.solvd.atm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.interfaces.IAccountDAO;
import com.solvd.atm.models.Account;

public class AccountMyBatisDao implements IAccountDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public AccountMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Account account) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.atm.mappers.AccountMapper.insert", account);
        }
    }

    @Override
    public Account getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.atm.mappers.AccountMapper.findById", id);
        }
    }

    @Override
    public List<Account> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.atm.mappers.AccountMapper.findAll");
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.atm.mappers.AccountMapper.update", account);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.atm.mappers.AccountMapper.delete", id);
        }
    }

    @Override
    public List<Account> findByUserId(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.atm.mappers.AccountMapper.findByUserId", userId);
        }
    }

    @Override
    public void updateBalance(int accountId, double newBalance) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            java.util.Map<String, Object> params = new java.util.HashMap<>();
            params.put("accountId", accountId);
            params.put("balance", newBalance);
            session.update("com.solvd.atm.mappers.AccountMapper.updateBalance", params);
        }
    }
}
