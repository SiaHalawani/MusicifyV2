package com.example.finalMusicify.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HeaderBar {

    public static HBox create(String titleText) {
        HBox topBar = new HBox();
        topBar.getStyleClass().add("top-bar");
        topBar.setPadding(new Insets(15));

        Label title = new Label(titleText);
        title.getStyleClass().add("top-bar-title");

        topBar.getChildren().add(title);
        return topBar;
    }
}
