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

    public StringBuilder getUserName(String firstName, String lastName) {
       return generateUserName(firstName, lastName);
    }
    private StringBuilder generateUserName(String firstname, String lastname) {
        StringBuilder userName = new StringBuilder();
        userName.append("@");
        userName.append(firstname.charAt(0));
        userName.append(lastname);
        userName.append(generateRandomNumbers());
        return userName;
    }

    private int generateRandomNumbers() {
        Random rand = new Random();
        return rand.nextInt(26);
    }

    private void onClientDashboard() {
        Stage stage = (Stage) create_acc_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }


}
