package com.github.austinfsse.sdev200.finalproject.Controllers;

import com.github.austinfsse.sdev200.finalproject.Models.DatabaseDriver;
import com.github.austinfsse.sdev200.finalproject.Models.Model;
import com.github.austinfsse.sdev200.finalproject.Models.User;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

import java.sql.*;

import java.util.ResourceBundle;

public class LoginController implements Initializable {

    DatabaseDriver driver = new DatabaseDriver();
    public String[] userInfo = new String[10];
    public TextField username_id;
    public TextField password_id;
    public Button createAccount_btn;
    public Button login_btn;
    public Button forgot_usr_btn;
    public Button forgot_pwd_btn;
    public Label error_lbl;
    private boolean successfulLogin = false;

    // Initializes button actions when the scene is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(e -> onLogin());
        createAccount_btn.setOnAction(e -> onCreateAccount());
        forgot_usr_btn.setOnAction(e -> onForgotUsername());
        forgot_pwd_btn.setOnAction(e -> onForgotPassword());
    }

    // Opens the Create Account screen and closes the current window
    private void onCreateAccount() {
        Stage stage = (Stage) createAccount_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showCreateAccountScreen();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    // Opens the Forgot Username screen and closes the current window
    private void onForgotUsername() {
        Stage stage = (Stage) createAccount_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showForgotInfo();
        stage.close();
    }

    // Opens the Forgot Password screen and closes the current window
    private void onForgotPassword() {
        Stage stage = (Stage) createAccount_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showForgotInfo();
        stage.close();
    }

    // Handles the login process by verifying credentials and showing the appropriate screens
    private void onLogin() {
        Stage stage = (Stage) login_btn.getScene().getWindow();
        String username = username_id.getText();
        String password = password_id.getText();
        userInfo = driver.retrieveRecord(username);

        User user = User.getInstance();
        user.setFirstName(userInfo[0]);
        user.setLastName(userInfo[1]);
        user.setEmail(userInfo[2]);
        user.setUsername(userInfo[3]);
        user.setPassword(userInfo[4]);
        user.setAccountNumber(userInfo[5]);
        user.setBalance(userInfo[6]);

        try {
            if (verifyLoginCredentials(username, password)) {
                Model.getInstance().getViewFactory().showClientDashboard();
                if (getSuccessfulLogin()) {
                    Model.getInstance().getViewFactory().closeStage(stage);
                }
            } else {
                if (driver.verifyDatabaseIsCreated()) {
                    String error = "Invalid username or password";
                    error_lbl.setText(error);
                } else {
                    Model.getInstance().getViewFactory().showCreateAccountScreen();
                    Model.getInstance().getViewFactory().closeStage(stage);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Verifies login credentials by querying the database
    private boolean verifyLoginCredentials(String username, String password) {
        String query = "SELECT username, password FROM people WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DatabaseDriver.getDbUrl());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                setSuccessfulLogin(true);
                return getSuccessfulLogin();
            } else {
                setSuccessfulLogin(false);
            }
        } catch (SQLException e) {
            System.out.println("Database does not recognize credentials");
            setSuccessfulLogin(false);
        }
        return getSuccessfulLogin();
    }

    // Returns whether login was successful
    public boolean getSuccessfulLogin() {
        return successfulLogin;
    }

    // Sets the successfulLogin flag
    public void setSuccessfulLogin(boolean successfulLogin) {
        this.successfulLogin = successfulLogin;
    }
}


