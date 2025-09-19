-- Database creation
DROP DATABASE IF EXISTS ATM;
CREATE DATABASE ATM;
USE ATM;

-- Drop existing tables if rerunning
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Bills;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Users;

-- 1. Users Table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    card_number VARCHAR(20) UNIQUE,
    pin VARCHAR(10),
    phone VARCHAR(15)
);

-- 2. Accounts Table
CREATE TABLE Accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    balance DECIMAL(12,2) DEFAULT 0,
    account_type VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 3. Transactions Table
CREATE TABLE Transactions (
    txn_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    txn_type VARCHAR(20),
    amount DECIMAL(12,2),
    status VARCHAR(20),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
);

-- 4. Bills Table
CREATE TABLE Bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    bill_type VARCHAR(50),
    amount DECIMAL(12,2),
    status VARCHAR(20) DEFAULT 'unpaid',
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Insert sample Users
INSERT INTO Users (name, card_number, pin, phone) VALUES
('Olena Krutyko', '1234567890123456', '1234', '555-1111'),
('Ihor Klimuk', '9876543210987654', '4321', '555-2222'),
('Martha Ladutko', '1111222233334444', '5678', '555-3333');

-- Insert sample Accounts
INSERT INTO Accounts (user_id, balance, account_type) VALUES
(1, 5000.00, 'savings'),
(1, 2000.00, 'current'),
(2, 8000.00, 'savings'),
(3, 1000.00, 'savings');

-- Insert sample Transactions
INSERT INTO Transactions (account_id, txn_type, amount, status) VALUES
(1, 'deposit', 2000.00, 'success'),
(1, 'withdraw', 500.00, 'success'),
(2, 'bill', 100.00, 'success'),
(3, 'withdraw', 300.00, 'failed'),
(4, 'deposit', 500.00, 'success');

-- Insert sample Bills
INSERT INTO Bills (user_id, bill_type, amount, status) VALUES
(1, 'electricity', 120.50, 'unpaid'),
(1, 'internet', 60.00, 'paid'),
(2, 'water', 45.75, 'unpaid'),
(2, 'electricity', 90.25, 'unpaid'),
(3, 'phone', 30.00, 'unpaid');

