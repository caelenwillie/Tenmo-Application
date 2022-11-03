package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.math.BigDecimal;

public interface AccountDao {

        BigDecimal getAccountBalance(int account_id);

        void updateBalance(BigDecimal bigDecimal);

}
