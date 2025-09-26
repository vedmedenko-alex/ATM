package com.solvd.atm.services.impl;

import java.util.List;

import com.solvd.atm.dao.impl.BillMyBatisDao;
import com.solvd.atm.models.Bill;
import com.solvd.atm.services.interfaces.IBillService;

public class BillService implements  IBillService{
	private final BillMyBatisDao billDAO;

	public BillService(BillMyBatisDao billDAO) {
		this.billDAO = billDAO;
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
