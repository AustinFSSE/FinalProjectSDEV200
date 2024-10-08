package com.github.austinfsse.sdev200.finalproject.Stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayStages {

    public void showLoginWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Client/Login.fxml"));
        createStage(fxmlLoader);
    }
    public void closeStage(Stage stage) {
        stage.close();
    }

    public void showCreateAccountScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Client/CreateAccount.fxml"));
        createStage(fxmlLoader);
    }
    public void showClientDashboard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Client/ClientDashboard.fxml"));
        createStage(fxmlLoader);
    }
    public void showForgotInfo() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/ForgotInfo.fxml"));
        createStage(loader);
    }


    private void createStage(FXMLLoader loader) {
        Scene scene;
        try {
            scene = new Scene(loader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("JavaFx FinalProject");
        stage.show();


    }

}
