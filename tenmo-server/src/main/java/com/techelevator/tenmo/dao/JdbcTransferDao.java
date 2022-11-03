package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public class JdbcTransferDao implements TransferDao {


    public Transfer getTransferByID(int id) {
        return null;
    }

    public Transfer getAllTransfer(int user_id) {
        return null;
    }

    public boolean createTransfer(Account account_from, Account account_to, BigDecimal amount) {
        return false;
    }

    public boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount) {
        return false;
    }
}
