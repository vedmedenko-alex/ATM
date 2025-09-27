package com.solvd.atm.services.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.atm.dao.impl.AccountMyBatisDao;
import com.solvd.atm.models.Account;
import com.solvd.atm.services.interfaces.IAccountService;
import com.solvd.atm.utils.MyBatisUtil;

public class AccountService implements IAccountService {

    private final AccountMyBatisDao accountDAO;

    public AccountService() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.accountDAO = new AccountMyBatisDao(sqlSessionFactory);
    }

    public List<Account> getAccountsByUser(int userId) throws Exception {
        return accountDAO.getByUserId(userId);
    }

    public Account getAccountById(int accountId) throws Exception {
        return accountDAO.getById(accountId);
    }

    public void updateBalance(int accountId, double newBalance) throws Exception {
        accountDAO.updateBalance(accountId, newBalance);
    }
}
