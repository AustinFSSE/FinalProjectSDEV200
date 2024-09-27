package com.github.austinfsse.sdev200.finalproject.Models;

import com.github.austinfsse.sdev200.finalproject.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;


    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
