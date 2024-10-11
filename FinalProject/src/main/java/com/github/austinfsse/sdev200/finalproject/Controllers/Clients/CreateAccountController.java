package com.github.austinfsse.sdev200.finalproject.Controllers.Clients;

import com.github.austinfsse.sdev200.finalproject.Models.DatabaseDriver;
import com.github.austinfsse.sdev200.finalproject.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    DatabaseDriver databaseDriver = new DatabaseDriver();

    public TextField fname_fld;
    public TextField lname_fld;
    public TextField email_fld;
    public TextField pwd_fld;
    public Button create_acc_btn;

    // Initializes the account creation screen and handles account creation when the button is clicked
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        create_acc_btn.setOnAction(e -> {
            databaseDriver.createTable();
            String firstName = fname_fld.getText();
            String lastName = lname_fld.getText();
            String email = email_fld.getText();
            String pwd = pwd_fld.getText();
            databaseDriver.insertRecord(firstName, lastName, email, pwd, getUserName(firstName, lastName));
            onClientDashboard();
        });
    }

    // Generates the username using the first and last names
    public StringBuilder getUserName(String firstName, String lastName) {
        return generateUserName(firstName, lastName);
    }

    // Helper method to construct a username based on the first letter of the first name, full last name, and random numbers
    private StringBuilder generateUserName(String firstname, String lastname) {
        StringBuilder userName = new StringBuilder();
        userName.append("@");
        userName.append(firstname.charAt(0));
        userName.append(lastname);
        userName.append(generateRandomNumbers());
        return userName;
    }

    // Generates a random number to append to the username
    private int generateRandomNumbers() {
        Random rand = new Random();
        return rand.nextInt(26);
    }

    // Navigates to the client dashboard after the account is successfully created
    private void onClientDashboard() {
        Stage stage = (Stage) create_acc_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}

