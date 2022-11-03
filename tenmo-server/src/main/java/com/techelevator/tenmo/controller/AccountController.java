package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.LoginDto;
import com.techelevator.tenmo.model.LoginResponseDto;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import com.techelevator.tenmo.model.Account;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountDao dao;

    public AccountController(AccountDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(@PathVariable int id) {
        return dao.getAccountBalance(id);
    }

    /*

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Account updateBalance(@RequestBody Account account, @PathVariable int id) // transferBalance parameter
    {
        BigDecimal updatedBalance = new BigDecimal(5000);
        Account updatedAccount = dao.updateBalance(account.getAccount_id(),updatedBalance);
        return updatedAccount;
    }

     */


}
