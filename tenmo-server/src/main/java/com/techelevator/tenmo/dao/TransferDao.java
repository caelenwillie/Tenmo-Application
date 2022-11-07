package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
        Transfer getTransferByID(int id);
        /* Added new method below */
        List<Transfer> getAllTransfers();

        Transfer createTransfer(Transfer transfer);

        //boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount);

}
