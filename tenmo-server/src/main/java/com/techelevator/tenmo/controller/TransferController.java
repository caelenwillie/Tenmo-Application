package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/* Changed from /transfer to /transfers */
@RequestMapping("/transfers")
@RestController
public class TransferController {

    // Step 2

    private TransferDao dao;
    public TransferController(TransferDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{transfer_id}", method = RequestMethod.GET)
    public Transfer getTransferByID(@PathVariable int transfer_id) {
        return dao.getTransferByID(transfer_id);
    }

    /* Added new mapping */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers() {
        return dao.getAllTransfers();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return dao.createTransfer(transfer);
    }



}
