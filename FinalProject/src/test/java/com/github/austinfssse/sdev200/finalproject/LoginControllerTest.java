package com.github.austinfssse.sdev200.finalproject;

import com.github.austinfsse.sdev200.finalproject.Controllers.LoginController;
import com.github.austinfsse.sdev200.finalproject.Models.DatabaseDriver;
import com.github.austinfsse.sdev200.finalproject.Models.Model;
import com.github.austinfsse.sdev200.finalproject.Models.User;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private DatabaseDriver mockDatabaseDriver;
    private TextField usernameField;
    private TextField passwordField;
    private Button loginButton;
    private Label errorLabel;

    @BeforeEach
    public void setUp() {
        mockDatabaseDriver = Mockito.mock(DatabaseDriver.class);
        loginController = new LoginController();
        loginController.driver = mockDatabaseDriver;

        usernameField = new TextField();
        passwordField = new TextField();
        loginButton = new Button();
        errorLabel = new Label();

        loginController.username_id = usernameField;
        loginController.password_id = passwordField;
        loginController.login_btn = loginButton;
        loginController.error_lbl = errorLabel;

    }

    @Test
    public void testSuccessfulLogin() throws SQLException {
        // Mocking user credentials
        String username = "testUser";
        String password = "testPass";
        usernameField.setText(username);
        passwordField.setText(password);

        String[] userInfo = { "John", "Doe", "john.doe@example.com", username, password, "123456", "1000" };
        when(mockDatabaseDriver.retrieveRecord(username)).thenReturn(userInfo);
        when(mockDatabaseDriver.verifyDatabaseIsCreated()).thenReturn(true);
        when(mockDatabaseDriver.getDbUrl()).thenReturn("jdbc:mock:db");

        // Simulating the database response for login verification
        when(loginController.verifyLoginCredentials(username, password)).thenReturn(true);

        // Perform login action
        loginController.onLogin();

        // Verify that the User instance was populated correctly
        User user = User.getInstance();
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());

        // Check if error label is still empty (indicating no errors)
        assertEquals("", errorLabel.getText());
    }

    @Test
    public void testFailedLogin() throws SQLException {
        // Mocking invalid user credentials
        String username = "wrongUser";
        String password = "wrongPass";
        usernameField.setText(username);
        passwordField.setText(password);

        when(mockDatabaseDriver.retrieveRecord(username)).thenReturn(new String[10]);
        when(mockDatabaseDriver.verifyDatabaseIsCreated()).thenReturn(true);
        when(mockDatabaseDriver.getDbUrl()).thenReturn("jdbc:mock:db");

        // Simulating the database response for login verification
        when(loginController.verifyLoginCredentials(username, password)).thenReturn(false);

        // Perform login action
        loginController.onLogin();

        // Check if the error message is set correctly
        assertEquals("Invalid username or password", errorLabel.getText());
    }

    @Test
    public void testCreateAccount() {
        // Mocking the stage
        Stage mockStage = mock(Stage.class);
        when(loginController.createAccount_btn.getScene().getWindow()).thenReturn(mockStage);

        // Perform create account action
        loginController.onCreateAccount();

        // Verify that the correct methods were called on the view factory
        verify(Model.getInstance().getViewFactory()).showCreateAccountScreen();
        verify(Model.getInstance().getViewFactory()).closeStage(mockStage);
    }
}
