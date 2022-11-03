package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getAccountBalance(int account_id) {
        BigDecimal accountBalance;
        try {
            String sql = "SELECT balance, FROM account WHERE account_id = ?;";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
            if (result.next()) {

            }
        } catch(Exception e){}
        return null;
    }

    public void updateBalance(BigDecimal bigDecimal) {



    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}
