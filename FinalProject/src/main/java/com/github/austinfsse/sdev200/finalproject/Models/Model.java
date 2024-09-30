package com.github.austinfsse.sdev200.finalproject.Models;

import com.github.austinfsse.sdev200.finalproject.Stages.DisplayStages;




public class Model {
    private static Model model;
    private final DisplayStages displayStages;
    private DatabaseDriver driver;
    private static boolean successfulLogin;


    private Model() {
        this.displayStages = new DisplayStages();
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public DisplayStages getViewFactory() {
        return displayStages;
    }

}
