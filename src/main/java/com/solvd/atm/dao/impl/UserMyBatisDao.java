package com.solvd.atm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.interfaces.IUserDAO;
import com.solvd.atm.models.User;

public class UserMyBatisDao implements IUserDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public UserMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(User user) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("UserMapper.insert", user);
        }
    }

    @Override
    public User getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("UserMapper.getById", id);
        }
    }

    @Override
    public List<User> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("UserMapper.findAll");
        }
    }

    @Override
    public void update(User user) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("UserMapper.update", user);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("UserMapper.delete", id);
        }
    }

    @Override
    public User getByCardAndPin(String cardNumber, String pin) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User params = new User();
            params.setCardNumber(cardNumber);
            params.setPin(pin);
            return session.selectOne("UserMapper.getByCardAndPin", params);
        }
    }
}
