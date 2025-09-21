package com.solvd.atm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.interfaces.IBillDAO;
import com.solvd.atm.models.Bill;

public class BillMyBatisDAO implements IBillDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public BillMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Bill bill) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.atm.mappers.BillMapper.insert", bill);
        }
    }

    @Override
    public Bill getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.atm.mappers.BillMapper.getById", id);
        }
    }

    @Override
    public List<Bill> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.atm.mappers.BillMapper.findAll");
        }
    }

    @Override
    public void update(Bill bill) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.atm.mappers.BillMapper.update", bill);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.atm.mappers.BillMapper.delete", id);
        }
    }

    @Override
    public List<Bill> findUnpaidByUser(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.atm.mappers.BillMapper.findUnpaidByUser", userId);
        }
    }

    @Override
    public void markAsPaid(int billId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.atm.mappers.BillMapper.markAsPaid", billId);
        }
    }
}
