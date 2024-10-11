package com.github.austinfsse.sdev200.finalproject.Models;

import com.github.austinfsse.sdev200.finalproject.Stages.DisplayStages;

// Model class implementing the Singleton design pattern.
public class Model {
    // Static instance variable to hold the single instance of Model.
    private static Model model;
    // Instance of DisplayStages, which manages the different stages of the application's user interface.
    private final DisplayStages displayStages;

    // Private constructor to prevent instantiation from outside the class.
    private Model() {
        // Initialize the DisplayStages instance for managing UI components.
        this.displayStages = new DisplayStages();
    }

    // Static method to get the single instance of Model.
    // Implements lazy initialization, creating the instance only when it's first requested.
    public static Model getInstance() {
        if (model == null) {
            model = new Model(); // Create a new instance if it doesn't exist.
        }
        return model; // Return the existing instance.
    }

    // Getter method to retrieve the DisplayStages instance.
    // Allows other classes to access the view factory for UI management.
    public DisplayStages getViewFactory() {
        return displayStages; // Return the DisplayStages instance.
    }

    // Other methods to interact with the DatabaseDriver and manage the successful login status
    // can be added here as needed.
}
