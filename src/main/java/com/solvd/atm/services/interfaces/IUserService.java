package com.solvd.atm.services.interfaces;

import com.solvd.atm.models.User;

public interface IUserService {

    public User login(String cardNumber, String pin) throws Exception;

    public void register(User user) throws Exception;

    public User getById(int id) throws Exception;

}
