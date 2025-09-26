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
            session.insert("AccountMapper.insert", account);
        }
    }

    @Override
    public Account getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("AccountMapper.getById", id);
        }
    }

    @Override
    public List<Account> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("AccountMapper.findAll");
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("AccountMapper.update", account);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("AccountMapper.delete", id);
        }
    }

    @Override
    public List<Account> getByUserId(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("AccountMapper.getByUserId", userId);
        }
    }

    @Override
    public void updateBalance(int accountId, double newBalance) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            java.util.Map<String, Object> params = new java.util.HashMap<>();
            params.put("accountId", accountId);
            params.put("balance", newBalance);
            session.update("AccountMapper.updateBalance", params);
        }
    }
}
