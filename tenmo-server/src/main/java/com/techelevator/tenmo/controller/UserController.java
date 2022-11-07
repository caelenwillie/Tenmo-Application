package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/* Changed from /user to /users */
@RequestMapping("/users")
public class UserController {

    // private TransferDao transferDao;
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User[] getAllUsers() {
        return userDao.findAll().toArray(User[]::new);
    }

    @RequestMapping(value = "{account_id}", method = RequestMethod.GET)
    public String getUsernameFromAccountId(@PathVariable int account_id) {
        return userDao.getUsernameFromAccountID(account_id);
    }


    @RequestMapping(value = "{user_id}/transfers", method = RequestMethod.GET)
    public Transfer[] getTransferForUserId(@PathVariable int user_id) {
        return userDao.getTransferForUserId(user_id);
    }

    @RequestMapping(value = "{user_id}/transfer/{transfer_id}", method = RequestMethod.GET)
    public List<Transfer> getTransferForUserIdTransferId(@PathVariable int user_id,
                                                         @PathVariable int transfer_id) {
        return userDao.getTransferForUserIdTransferId(user_id,transfer_id);
    }

}
