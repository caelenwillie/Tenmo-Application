package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
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

    public BigDecimal getAccountBalance(int user_id) {
        Account account = new Account();
        String sql = "SELECT * FROM account\n" +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id\n" +
                "WHERE account.user_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,user_id);
            while (result.next()) {
                account = mapRowToAccount(result);
            }
        } catch(Exception e){}
        return account.getBalance();
    }

    public int getAccountIdFromUserId(int accountId) {
        Account account = new Account();
        String sql = "select * from account\n" +
                "where user_id =?\n";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,accountId);
            while (result.next()) {
                account = mapRowToAccount(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account.getAccount_id();
    }


    @Override
    public void updateAccountBalance(Transfer transfer) {
        String sql = "BEGIN TRANSACTION;\n" +
                "UPDATE account SET balance = balance - ? WHERE account_id = ?;\n" +
                "UPDATE account SET balance = balance + ? WHERE account_id = ?;\n" +
                "UPDATE transfer SET transfer_type_id = 2, transfer_status_id = 2 WHERE transfer_id = ?;\n" +
                "COMMIT;";
        try {
            jdbcTemplate.update(sql,transfer.getAmount(), transfer.getAccount_from(), transfer.getAmount(),
                    transfer.getAccount_to(),transfer.getTransfer_id());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}
