package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TransferService {

    private String API_Base_URL = "http://localhost:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String API_Base_URL) {
        this.API_Base_URL = API_Base_URL;
    }

    public Transfer[] getAllTransfers(String token) {
        Transfer[] transfers = null;
        try{
            transfers = restTemplate.exchange(API_Base_URL + "/transfers", HttpMethod.POST, authenticationEntity(token), Transfer[].class).getBody();

        }catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());

        }

        return transfers;}

    public void printTransferDetails(int transfer_id) {
        try {
            Transfer transfer = restTemplate.getForObject(API_Base_URL + "transfers/" + transfer_id,Transfer.class);
            System.out.println("--------------------------------------------\n" +
                    "Transfer Details\n" +
                    "--------------------------------------------\n");
            System.out.println("Id: " + transfer.getTransfer_id());
            System.out.println("From: " + transfer.getAccount_from());
            System.out.println("To: " + transfer.getAccount_to());
            System.out.println("Type: " + transfer.getTransfer_type_id());
            System.out.println("Status: " + transfer.getTransfer_status_id());
            System.out.println("Amount: " + transfer.getAmount());
        } catch (Exception e){
            System.out.println("Id does not exist on table");
        }
    }

    public Transfer createTransfer(Transfer transfer, String token){
        try{
            restTemplate.postForObject(API_Base_URL + "/transfers",transfer,Transfer.class);
        } catch(RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }


    public Transfer[] listTransfersFromUserID(int userID) {
        Transfer[] transferList;
        transferList = restTemplate.getForObject(API_Base_URL+ "users/"+userID+"/transfers",Transfer[].class);
        return transferList;
    }


    private HttpEntity authenticationEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

    private HttpEntity<Transfer> transferEntity(Transfer transfer, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }


}
