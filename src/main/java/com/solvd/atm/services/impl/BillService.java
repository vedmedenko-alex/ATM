package com.solvd.atm.services.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.impl.BillMyBatisDao;
import com.solvd.atm.models.Bill;
import com.solvd.atm.services.interfaces.IBillService;
import com.solvd.atm.utils.MyBatisUtil;

public class BillService implements IBillService {

    private final BillMyBatisDao billDAO;

    public BillService() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.billDAO = new BillMyBatisDao(sqlSessionFactory);
    }

    /**
     * Pays a bill by marking it as paid in the database.
     *
     * @param billId the id of the bill to pay
     */
    public void payBill(int billId) throws Exception {
        billDAO.markAsPaid(billId);
    }

    /**
     * Returns all unpaid bills for a user.
     *
     * @param userId the user's id
     * @return list of unpaid bills
     */
    public List<Bill> getUnpaidBillsForUser(int userId) {
        return billDAO.findUnpaidByUser(userId);
    }

    /**
     * Returns all bills for a user.
     *
     * @param userId the user's id
     * @return list of bills
     */
    public List<Bill> getAllBillsForUser(int userId) {
        List<Bill> allBills = billDAO.getAll();
        return allBills.stream().filter(b -> b.getUserId() == userId).toList();
    }
}
