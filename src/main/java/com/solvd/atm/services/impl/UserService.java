package com.solvd.atm.services.impl;

import com.solvd.atm.dao.interfaces.IUserDAO;
import com.solvd.atm.models.User;
import com.solvd.atm.services.interfaces.IUserService;

public class UserService implements IUserService {

    private final IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
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
