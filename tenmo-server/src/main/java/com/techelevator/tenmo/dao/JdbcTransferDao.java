package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    // Step 1

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Added the following method to retrieve all transfers */
    public List<Transfer> getAllTransfers() {
        String sql = "SELECT * FROM transfer";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Transfer> transfers = new ArrayList<>();
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    // Get Request
    public List<Transfer> getTransferByID(int transfer_id) {
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transfer_id);
        List<Transfer> transfers = new ArrayList<>();
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }



    /* Moved the following methods to JdbcUserDao
    public List<Transfer> getTransferForUserIdTransferId(int user_id, int transfer_id) {
        String sql = "SELECT * FROM transfer " +
                "JOIN accounts ON accounts.account_id = transfers.account_from OR accounts.account_id = transfers.account_to " +
                "WHERE user_id = ? AND transfer_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id, transfer_id);
        List<Transfer> transfers = new ArrayList<>();
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    // Get Request
    public List<Transfer> getTransferForUserId(int user_id) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer " +
                    "JOIN account ON account.account_id = transfer.account_from OR account.account_id = transfer.account_to " +
                    "WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,user_id);
            while(results.next()){
                transfers.add(mapRowToTransfer(results));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return transfers;
    }
    */

    // Step 1
    //Post Request
    public Transfer createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ? ,?, ?)";
        try{
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,transfer.getTransfer_id(),transfer.getTransfer_type_id(),transfer.getTransfer_status_id(), transfer.getAccount_from(),transfer.getAccount_to(), transfer.getAmount());
        jdbcTemplate.update(sql,results);
        while (results.next()){
            transfer = mapRowToTransfer(results);
        }
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        return transfer;

    }

    /*
    public boolean requestTransfer(Account account_from, Account account_to, BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        return false;
    }
    */

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
