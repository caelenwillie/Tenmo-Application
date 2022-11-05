package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
        List<Transfer> getTransferByID(int id);
        /* Added new method below */
        List<Transfer> getAllTransfers();

        Transfer createTransfer(Transfer transfer);

        //boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount);

        /* Moved the following methods to UserDao
        List<Transfer> getTransferForUserId(int user_id);
        List<Transfer> getTransferForUserIdTransferId(int user_id, int transfer_id);
        */
}
