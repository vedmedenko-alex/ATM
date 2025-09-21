package com.solvd.atm.dao.interfaces;

import com.solvd.atm.models.User;

public interface IUserDAO extends IGenericDAO<User> {

    User getByCardAndPin(String cardNumber, String pin) throws Exception;
}
