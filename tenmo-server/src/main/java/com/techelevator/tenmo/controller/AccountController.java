package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountDao dao;

    public AccountController(AccountDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(@PathVariable int id) {
        return dao.getAccountBalance(id);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public void updateAccountBalance(@RequestBody Transfer transfer) {
        dao.updateAccountBalance(transfer);
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
    public int getAccountIdFromUserId(@PathVariable int userID) {
        return dao.getAccountIdFromUserId(userID);
    }

}
