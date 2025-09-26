package com.solvd.atm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.interfaces.IBillDAO;
import com.solvd.atm.models.Bill;

public class BillMyBatisDao implements IBillDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public BillMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Bill bill) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("BillMapper.insert", bill);
        }
    }

    @Override
    public Bill getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("BillMapper.getById", id);
        }
    }

    @Override
    public List<Bill> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("BillMapper.findAll");
        }
    }

    @Override
    public void update(Bill bill) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("BillMapper.update", bill);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("BillMapper.delete", id);
        }
    }

    @Override
    public List<Bill> findUnpaidByUser(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("BillMapper.findUnpaidByUser", userId);
        }
    }

    @Override
    public void markAsPaid(int billId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("BillMapper.markAsPaid", billId);
        }
    }
}
