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

    public Transfer createTransfer(Transfer transfer, String token){
        try{
            restTemplate.exchange(API_Base_URL + "/transfers", HttpMethod.POST, authenticationEntity(token), Transfer.class).getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return transfer;
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
