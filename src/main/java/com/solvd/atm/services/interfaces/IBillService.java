package com.solvd.atm.services.interfaces;

import java.util.List;

import com.solvd.atm.models.Bill;

public interface IBillService {

    public void payBill(int billId) throws Exception;

    public List<Bill> getUnpaidBillsForUser(int userId) throws Exception;

    public List<Bill> getAllBillsForUser(int userId) throws Exception;

}
