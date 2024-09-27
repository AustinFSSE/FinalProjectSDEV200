package com.github.austinfsse.sdev200.finalproject.Controllers;

import com.github.austinfsse.sdev200.finalproject.Models.Model;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;

import java.util.ResourceBundle;

public class LoginController  implements Initializable {
    public TextField username_id;
    public TextField password_id;
    public Button createAccount_btn;
    public Button login_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(e -> onLogin());
        createAccount_btn.setOnAction(e -> onCreateAccount());
    }

    private void onCreateAccount() {
        Model.getInstance().getViewFactory().showCreateAccountScreen();
    }

    private void onLogin() {

        Model.getInstance().getViewFactory().showClientDashboard();
    }


}

