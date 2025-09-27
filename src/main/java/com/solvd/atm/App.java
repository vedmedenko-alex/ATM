package com.solvd.atm;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.atm.dao.impl.AccountMyBatisDao;
import com.solvd.atm.dao.impl.BillMyBatisDao;
import com.solvd.atm.dao.impl.TransactionMyBatisDao;
import com.solvd.atm.dao.impl.UserMyBatisDao;
import com.solvd.atm.models.Account;
import com.solvd.atm.models.Transaction;
import com.solvd.atm.models.User;
import com.solvd.atm.services.impl.ATMService;
import com.solvd.atm.services.impl.AccountService;
import com.solvd.atm.services.impl.BillService;
import com.solvd.atm.services.impl.TransactionService;
import com.solvd.atm.services.impl.UserService;

public class App {

    public static void main(String[] args) throws Exception{


        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        BillService billService = new BillService();

        ATMService atmService = new ATMService(userService, accountService, billService, transactionService);

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        // Login
        while (currentUser == null) {
            System.out.println("Enter card number:");
            String card = scanner.nextLine();
            System.out.println("Enter PIN:");
            String pin = scanner.nextLine();

            currentUser = atmService.login(card, pin);
            if (currentUser == null) {
                System.out.println("Invalid card or PIN. Try again.");
            }
        }
        /*
        ('Olena Krutyko', '1234567890123456', '1234', '555-1111'),
        ('Ihor Klimuk', '9876543210987654', '4321', '555-2222'),
        ('Martha Ladutko', '1111222233334444', '5678', '555-3333'); 
        */

        System.out.println("Welcome, " + currentUser.getName() + "!");

        // Main Menu
        boolean running = true;
        while (running) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw money");
            System.out.println("3. Deposit money");
            System.out.println("4. Pay bill");
            System.out.println("5. Transaction history");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: // Check balance
                    List<Account> accounts = atmService.checkBalance(currentUser.getUserId());
                    accounts.forEach(acc
                            -> System.out.println("Account #" + acc.getAccountId()
                                    + " (" + acc.getAccountType() + "): " + acc.getBalance() + " USD"));
                    break;

                case 2: // Withdraw
                    System.out.print("Enter account ID: ");
                    int accIdW = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter amount: ");
                    double amountW = Double.parseDouble(scanner.nextLine());
                    if (atmService.withdraw(accIdW, amountW)) {
                        System.out.println("You withdrew " + amountW + " USD.");
                    } else {
                        System.out.println("Withdrawal failed.");
                    }
                    break;

                case 3: // Deposit
                    System.out.print("Enter account ID: ");
                    int accIdD = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter amount: ");
                    double amountD = Double.parseDouble(scanner.nextLine());
                    if (atmService.deposit(accIdD, amountD)) {
                        System.out.println("You deposited " + amountD + " USD.");
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;

                case 4: // Pay bill
                    System.out.print("Enter account ID: ");
                    int accIdB = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter bill ID: ");
                    int billId = Integer.parseInt(scanner.nextLine());
                    if (atmService.payBill(accIdB, billId)) {
                        System.out.println("Bill #" + billId + " paid successfully.");
                    } else {
                        System.out.println("Bill payment failed.");
                    }
                    break;

                case 5: // Transaction history
                    System.out.print("Enter account ID: ");
                    int accIdT = Integer.parseInt(scanner.nextLine());
                    List<Transaction> txns = atmService.getLastTransactions(accIdT, 5);
                    txns.forEach(txn
                            -> System.out.println(txn.getTimestamp() + " | " + txn.getTxnType()
                                    + " | " + txn.getAmount() + " USD | " + txn.getStatus()));
                    break;

                case 0:
                    running = false;
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
