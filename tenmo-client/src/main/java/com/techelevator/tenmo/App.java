package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.accountService;
import org.springframework.web.client.RestTemplate;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final accountService accountService = new accountService(API_BASE_URL);
    private final ConsoleService consoleService = new ConsoleService();
    private final TransferService transferService = new TransferService(API_BASE_URL);
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;

    private RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        BigDecimal accountBalance = restTemplate.getForObject("http://localhost:8080/accounts/" + currentUser.getUser().getId(), BigDecimal.class);
        System.out.println("Your current account balance is: " + accountBalance);
    }

	private void viewTransferHistory() {
        try {
            Transfer[] transferHistory;
            transferHistory = transferService.listTransfersFromUserID(currentUser.getUser().getId());
            System.out.println("--------------------------");
            System.out.println("Transfers");
            System.out.println("ID     From/To     Amount");
            System.out.println("---------------------------");
            int currentUserID = currentUser.getUser().getId();
            for(Transfer transfer : transferHistory) {
                System.out.println(transfer.getTransfer_id() + "  " + accountService.getUsernameForAccountID(transfer.getAccount_from()) + "  " + accountService.getUsernameForAccountID(transfer.getAccount_to()) + "  " + transfer.getAmount());
            }
            int transferidVal = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
            if (transferidVal == 0) {
                System.out.println("Exiting transfer history");
            } else {
                transferService.printTransferDetails(transferidVal);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		// TODO Auto-generated method stub
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        User[] userList = accountService.getAllUsers();
        for (User user : userList) {
            if (user != currentUser.getUser()) {
                System.out.println("ID: " + user.getId() + " | " + user.getUsername());
            }
        }
        int userIdSelect = consoleService.promptForInt("Enter the ID of the user to send money to:");
        viewCurrentBalance();
        BigDecimal amountToSend = consoleService.promptForBigDecimal("Enter the amount to send:");
        Random rand = new Random();
        Transfer transfer = new Transfer(Math.abs(rand.nextInt()),2,1, accountService.getAccountIdFromUserId(userIdSelect),accountService.getAccountIdFromUserId(currentUser.getUser().getId()),amountToSend);
        transferService.createTransfer(transfer, currentUser.getToken());
	}

	private void requestBucks() {
        User[] userList = accountService.getAllUsers();
        for (User user : userList) {
            if (user != currentUser.getUser()) {
                System.out.println("ID: " + user.getId() + " | " + user.getUsername());
            }
        }
        int userIdSelect = consoleService.promptForInt("Enter the ID of the user to request money from:");
        viewCurrentBalance();
        BigDecimal amountToSend = consoleService.promptForBigDecimal("Enter the amount to request");
        Random rand = new Random();
        Transfer transfer = new Transfer(Math.abs(rand.nextInt()),1,1, accountService.getAccountIdFromUserId(userIdSelect),accountService.getAccountIdFromUserId(currentUser.getUser().getId()),amountToSend);
        transferService.createTransfer(transfer, currentUser.getToken());
	}

}
