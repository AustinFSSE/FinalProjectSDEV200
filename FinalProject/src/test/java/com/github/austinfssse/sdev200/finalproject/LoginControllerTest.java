package com.github.austinfssse.sdev200.finalproject;


import com.github.austinfsse.sdev200.finalproject.Controllers.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class LoginControllerTest {

    private Stage primaryStage;
    private FxRobot robot;
    public LoginController loginController;
    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/FXML/Client/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void testLogin() {
        assertNotNull(loginController, "LoginController is null");

        robot.clickOn("#login_btn");

        assertTrue(primaryStage.isShowing());
    }
}
