package com.solvd.atm.services.impl;

import java.util.List;
import java.util.Random;

import com.solvd.atm.models.Account;
import com.solvd.atm.models.Bill;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.models.User;

public class ATMService {

    private final UserService userService;
    private final AccountService accountService;
    private final BillService billService;
    private final TransactionService transactionService;

    private final Random random = new Random();

    public ATMService(UserService userService,
            AccountService accountService,
            BillService billService,
            TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.billService = billService;
        this.transactionService = transactionService;
    }

    public User login(String cardNumber, String pin) throws Exception {
        return userService.login(cardNumber, pin);
    }

    public List<Account> checkBalance(int userId) throws Exception {
        return accountService.getAccountsByUser(userId);
    }

    public boolean withdraw(int accountId, double amount) throws Exception {
        if (simulateBankOffline()) {
            return false;
        }

        Account acc = accountService.getAccountById(accountId);
        if (acc.getBalance() < amount) {
            return false;
        }

        accountService.updateBalance(accountId, acc.getBalance() - amount);

        Transaction txn = new Transaction();
        txn.setAccountId(accountId);
        txn.setTxnType("WITHDRAW");
        txn.setAmount(amount);
        txn.setStatus("SUCCESS");
        // вывать транзакшен сервис

        return true;
    }

    public boolean deposit(int accountId, double amount) throws Exception {
        if (simulateBankOffline()) {
            return false;
        }

        Account acc = accountService.getAccountById(accountId);
        accountService.updateBalance(accountId, acc.getBalance() + amount);

        Transaction txn = new Transaction();
        txn.setAccountId(accountId);
        txn.setTxnType("DEPOSIT");
        txn.setAmount(amount);
        txn.setStatus("SUCCESS");
        transactionService.addTransaction(txn);

        System.out.println("Deposited " + amount + " to account " + accountId);
        return true;
    }

    public boolean payBill(int accountId, int billId) throws Exception {
        if (simulateBankOffline()) {
            return false;
        }

        Bill bill = billService.getAllBillsForUser(accountService.getAccountById(accountId).getUserId())
                .stream().filter(b -> b.getBillId() == billId).findFirst().orElse(null);
        if (bill == null || !"unpaid".equalsIgnoreCase(bill.getStatus())) {
            System.out.println("Bill not found or already paid.");
            return false;
        }

        Account acc = accountService.getAccountById(accountId);
        if (acc.getBalance() < bill.getAmount()) {
            System.out.println("Insufficient funds to pay bill.");
            return false;
        }

        accountService.updateBalance(accountId, acc.getBalance() - bill.getAmount());
        billService.payBill(billId);

        Transaction txn = new Transaction();
        txn.setAccountId(accountId);
        txn.setTxnType("PAY_BILL");
        txn.setAmount(bill.getAmount());
        txn.setStatus("SUCCESS");
        transactionService.addTransaction(txn);

        System.out.println("Paid bill " + billId + " from account " + accountId);
        return true;
    }

    public List<Transaction> getLastTransactions(int accountId, int limit) throws Exception {
        return transactionService.getLastTransactions(accountId, limit);
    }

    private boolean simulateBankOffline() {
        return random.nextInt(10) < 2; // 20% шанс оффлайна
    }
}
