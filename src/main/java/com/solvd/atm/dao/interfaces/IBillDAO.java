package com.solvd.atm.dao.interfaces;

import java.util.List;

import com.solvd.atm.models.Bill;

public interface IBillDAO extends IGenericDAO<Bill> {

    List<Bill> findUnpaidByUser(int userId) throws Exception;

    void markAsPaid(int billId) throws Exception;

}
