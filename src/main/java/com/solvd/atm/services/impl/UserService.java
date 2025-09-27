package com.solvd.atm.services.impl;

import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.impl.UserMyBatisDao;
import com.solvd.atm.dao.interfaces.IUserDAO;
import com.solvd.atm.models.User;
import com.solvd.atm.services.interfaces.IUserService;
import com.solvd.atm.utils.MyBatisUtil;

public class UserService implements IUserService {

    private final IUserDAO userDAO;

    public UserService() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.userDAO = new UserMyBatisDao(sqlSessionFactory);
    }

    public User login(String cardNumber, String pin) throws Exception {
        return userDAO.getByCardAndPin(cardNumber, pin);
    }

    public void register(User user) throws Exception {
        userDAO.insert(user);
    }

    public User getById(int id) throws Exception {
        return userDAO.getById(id);
    }
}
