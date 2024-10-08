package com.github.austinfsse.sdev200.finalproject.Controllers.Clients;


import com.github.austinfsse.sdev200.finalproject.Models.DatabaseDriver;
import com.github.austinfsse.sdev200.finalproject.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ForgotLoginInfo implements Initializable {
    public Button submit;
    public TextField fname_fld;
    public TextField lname_fld;
    public TextField email_fld;
    public Label error_lbl;
    public Label usr_pwd_lbl;
    public Button login_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit.setOnAction(e -> onSubmit());
        login_btn.setOnAction(e -> {
            Stage stage = (Stage) login_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().showLoginWindow();
            stage.close();
        });
    }

    private void onSubmit() {

        String fname = fname_fld.getText();
        String lname = lname_fld.getText();
        String email = email_fld.getText();
        String query = "SELECT * FROM people WHERE firstname= ? AND lastname= ? AND email= ?";
        try (Connection conn = DriverManager.getConnection(DatabaseDriver.getDbUrl());
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("firstname"));
                System.out.println(rs.getString("lastname"));
                System.out.println(rs.getString("email"));
                usr_pwd_lbl.setText("Great! Username is " +
                        rs.getString("username") + " Password is " +
                        rs.getString("password"));
            } else {
                error_lbl.setVisible(true);
                error_lbl.setText("Invalid credentials");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
