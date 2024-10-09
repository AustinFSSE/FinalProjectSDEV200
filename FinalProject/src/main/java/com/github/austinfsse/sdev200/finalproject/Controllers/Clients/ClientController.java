package com.github.austinfsse.sdev200.finalproject.Controllers.Clients;

import com.github.austinfsse.sdev200.finalproject.Models.DatabaseDriver;
import com.github.austinfsse.sdev200.finalproject.Models.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    DatabaseDriver driver = new DatabaseDriver();

    public Label greeting_user_lbl;
    public Label usr_balance_lbl;
    public Label usr_acc_lbl;
    public Label usr_fname_lbl;
    public Label usr_lname_lbl;
    public Label usr_email_lbl;
    public Label current_ba_lbl;
    public TextField money_fld;
    public Button withdraw_btn;
    public Button deposit_btn;
    User user = User.getInstance();
    String[] userInfo = new String[7];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Client");
        userInfo = driver.retrieveRecord(user.getUsername());
        greeting_user_lbl.setText("Welcome, " + user.getUsername() + "!");
        usr_balance_lbl.setText("Balance: " + user.getBalance());
        usr_acc_lbl.setText("Account Number: " + user.getAccountNumber());
        usr_fname_lbl.setText("First Name: " + user.getFirstName());
        usr_lname_lbl.setText("Last Name: " + user.getLastName());
        usr_email_lbl.setText("Email: " + user.getEmail());
        current_ba_lbl.setText("Current Balance: " + user.getBalance());

        deposit_btn.setOnAction(e -> {
            onDeposit();
        });
        withdraw_btn.setOnAction(e -> {
            onWithdraw();
        });


    }

    private void onWithdraw() {
        int balance = Integer.parseInt(user.getBalance());
        int money = Integer.parseInt(money_fld.getText());
        if (money < 0) {
            System.out.println("Negative amount. Please enter a positive amount to withdraw.");
            return;
        }
        if (money > balance) {
            System.out.println("Insufficient funds. Please enter a smaller amount.");
            return;
        }
        balance -= money;
        user.setBalance(String.valueOf(balance));
        money_fld.setText("");
        driver.updateBalance(user.getUsername(), balance); // Update the balance in the database
    }

    private void onDeposit() {
        int balance = Integer.parseInt(user.getBalance());
        int money = Integer.parseInt(money_fld.getText());

        if (money < 0) {
            System.out.println("Negative amount. Please enter a positive amount to deposit.");
            return;
        }
        balance += money;
        user.setBalance(String.valueOf(balance));
        money_fld.setText("");
        driver.updateBalance(user.getUsername(), balance); // Update the balance in the database
    }
}
