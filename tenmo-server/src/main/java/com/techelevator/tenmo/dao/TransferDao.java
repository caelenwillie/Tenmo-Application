package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface TransferDao {
        Transfer getTransferByID(int id);
        Transfer getAllTransfer(int user_id);
        boolean createTransfer(Account account_from, Account account_to, BigDecimal amount);
        boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount);

}
