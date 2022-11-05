package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int id);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);

    /* Moved the following methods from transferdao to userdao */
    List<Transfer> getTransferForUserId(int user_id);
    List<Transfer> getTransferForUserIdTransferId(int user_id, int transfer_id);
}
