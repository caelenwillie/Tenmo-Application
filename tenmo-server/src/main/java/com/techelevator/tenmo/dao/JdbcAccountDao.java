package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;


@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getAccountBalance(int account_id) {
        Account account = new Account();
        String sql = "SELECT * FROM account WHERE account_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,account_id);
            while (result.next()) {
                account = mapRowToAccount(result);
            }
        } catch(Exception e){}
        System.out.println(account.toString());
        return account.getBalance();
    }

    public Account updateBalance(int account_id, BigDecimal bigDecimal) {
        Account account = new Account();
        String sql = " UPDATE account set balance = ? WHERE account_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,bigDecimal, account_id);
            while (result.next()) {
                account = mapRowToAccount(result);
            }
        } catch(Exception e){}
        return account;
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}
