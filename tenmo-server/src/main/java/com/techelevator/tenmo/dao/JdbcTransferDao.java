package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        Transfer transfer = new Transfer();
        String sql = "INSERT INTO transfer (account_from, account_to, amount) VALUES (?, ?, ?) returning transfer_id";
        try{
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account_from, account_to, amount);
        while (results.next()){
            transfer = mapRowToTransfer(results);
            return true;
        }
        }catch (Exception e){}
        return false;
    }


    //Last Step
    //Post Request
    public boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        return false;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransfer_id(rs.getInt("transfer_id"));
        transfer.setTransfer_type_id(rs.getInt("transfer_type_id"));
        transfer.setTransfer_status_id(rs.getInt("transfer_status_id"));
        transfer.setAccount_from(rs.getInt("account_from"));
        transfer.setAccount_to(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }
}
