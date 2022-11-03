package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcTransferDao implements TransferDao {

    // Step 1

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get Request
    public Transfer getTransferByID(int id) {
        return null;
    }

    // Get Request
    public Transfer getAllTransfer(int user_id) {
        return null;
    }


    // Step 1
    //Post Request
    public boolean createTransfer(Account account_from, Account account_to, BigDecimal amount) {
        return false;
    }


    //Last Step
    //Post Request
    public boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount) {
        return false;
    }
}
