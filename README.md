# ATM Simulation Project

## Project Overview:
-----------------
This Java-based ATM simulation project provides a simple command-line interface for users to perform basic banking operations such as checking balance, depositing funds, withdrawing money, and exiting the system. It is designed for educational purposes to demonstrate object-oriented programming, user input handling, and modular design in Java.

## Features:
---------
- User authentication (PIN-based login)
- Balance inquiry
- Deposit and withdrawal functionality
- Transaction history (optional)
- Input validation and error handling
- Modular code structure using classes (e.g., ATM, Account, Transaction)

## Technologies Used:
------------------
- Java 17+
- MyBatis (XML mappers)
- MySQL (or any relational DB)
- Maven for dependency management

## Project Structure:
------------------
```
src/main/java/com/solvd/atm/
│
├── App.java                 # Entry point (console UI)
│
├── dao/
│   ├── interfaces/          # DAO interfaces (IGenericDAO, IUserDAO, IAccountDAO, etc.)
│   └── mybatisimpl/         # MyBatis implementations of DAOs
│
├── models/                  # POJO models (User, Account, Bill, Transaction)
│
├── service/                 # Service layer
│   ├── interfaces/          # Service interfaces
│   └── impl/ 
│      ├── ATMService.java  # ATM facade
│      ├── UserService.java
│      ├── AccountService.java
│      ├── BillService.java
│      └── TransactionService.java
│
└── resources/
    ├── mybatis-config.xml   # MyBatis configuration
    └── mappers/             # MyBatis XML mappers (UserMapper.xml, AccountMapper.xml, etc.)

```

## How to Run:
-----------
1. Clone the repository
```
git clone https://github.com/vedmedenko-alex/ATM.git
cd ATM
```
2. Configure database connection in resources/mybatis-config.xml.

3. Run database schema script (atm.sql).

4. Build and run the project:
```
mvn clean install
mvn exec:java -D"exec.mainClass=com.solvd.atm.App" 
```
5. Use the console menu to interact with the ATM.


## Customization:
--------------
- You can modify the initial account balance or add multiple accounts.
- Extend functionality by adding features like fund transfer, account creation, or GUI.

## Authors:
--------
Created by
Karastan Vladislav
Oleksandr Vedmedenko

## License:
--------
This project is open-source and free to use for educational and non-commercial purposes.

Date: September 2025
