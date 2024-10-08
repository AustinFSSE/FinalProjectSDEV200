package com.github.austinfsse.sdev200.finalproject;

import com.github.austinfsse.sdev200.finalproject.Models.DatabaseDriver;
import com.github.austinfsse.sdev200.finalproject.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        DatabaseDriver driver = new DatabaseDriver();

        if (!driver.verifyDatabaseIsCreated()) {
            Model.getInstance().getViewFactory().showCreateAccountScreen();
        } else {
            Model.getInstance().getViewFactory().showLoginWindow();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

