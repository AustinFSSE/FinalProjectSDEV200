package com.github.austinfsse.sdev200.finalproject;

import com.github.austinfsse.sdev200.finalproject.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}

