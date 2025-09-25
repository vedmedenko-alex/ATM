package com.solvd.atm.services.impl;

import com.solvd.atm.dao.impl.BillMyBatisDAO;
import com.solvd.atm.models.Bill;
import java.util.List;

public class BillService {
	private final BillMyBatisDAO billDAO;

	public BillService(BillMyBatisDAO billDAO) {
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
