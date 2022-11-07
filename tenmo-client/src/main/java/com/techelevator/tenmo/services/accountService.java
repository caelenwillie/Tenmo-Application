package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class accountService {

    private String API_Base_URL = "http://localhost:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    public accountService(String API_Base_URL) {
        this.API_Base_URL = API_Base_URL;
    }

    public User[] getAllUsers(){
        User[] userList;
        userList = restTemplate.getForObject(API_Base_URL+ "users",User[].class);
        return userList;
    }

    public BigDecimal getAccountBalance(User user) {
        BigDecimal accountBalance = restTemplate.getForObject("http://localhost:8080/accounts/" + user.getId(), BigDecimal.class);
        return accountBalance;
    }

    public String getUsernameForAccountID(int accountId) {
        String username = "";
        username = restTemplate.getForObject(API_Base_URL + "users/" + accountId,String.class);
        return username;

    }

    public int getAccountIdFromUserId(int userId) {
        userId = restTemplate.getForObject(API_Base_URL + "/accounts/user/" + userId,int.class);
        return userId;
    }

}
