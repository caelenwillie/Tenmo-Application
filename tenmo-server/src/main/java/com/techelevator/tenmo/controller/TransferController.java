package com.techelevator.tenmo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/transfer")
public class TransferController {

    // Step 2

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BigDecimal getTransferForId(@PathVariable int id) {
        return dao.getAccountBalance(id);
    }



}
