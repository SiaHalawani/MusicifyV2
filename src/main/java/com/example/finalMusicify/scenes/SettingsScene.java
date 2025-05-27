package com.example.finalMusicify.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsScene {

    public static Pane createView(Scene mainScene) {
        VBox wrapper = new VBox(25);
        wrapper.setPadding(new Insets(40));
        wrapper.setAlignment(Pos.TOP_LEFT);
        wrapper.getStyleClass().add("glass-card");

        Label title = new Label("⚙ Settings");
        title.getStyleClass().add("section-title");

        // === Email Display ===
        Label emailLabel = new Label("📧 Email: user@example.com");
        emailLabel.getStyleClass().add("form-subtitle");

        // === Dark/Light Mode Radio Toggle ===
        Label themeLabel = new Label("🌓 Select Theme:");

        RadioButton darkRadio = new RadioButton("🌙 Dark");
        RadioButton lightRadio = new RadioButton("☀️ Light");
        darkRadio.getStyleClass().add("form-input");
        lightRadio.getStyleClass().add("form-input");

        ToggleGroup themeGroup = new ToggleGroup();
        darkRadio.setToggleGroup(themeGroup);
        lightRadio.setToggleGroup(themeGroup);

        // Preselect current theme
        boolean isDark = mainScene.getRoot().getStyleClass().contains("dark-mode");
        if (isDark) {
            darkRadio.setSelected(true);
        } else {
            lightRadio.setSelected(true);
        }

        // Theme toggle action
        themeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == darkRadio) {
                mainScene.getRoot().getStyleClass().removeAll("light-mode");
                if (!mainScene.getRoot().getStyleClass().contains("dark-mode")) {
                    mainScene.getRoot().getStyleClass().add("dark-mode");
                }
            } else if (newToggle == lightRadio) {
                mainScene.getRoot().getStyleClass().removeAll("dark-mode");
                if (!mainScene.getRoot().getStyleClass().contains("light-mode")) {
                    mainScene.getRoot().getStyleClass().add("light-mode");
                }
            }
        });

        HBox themeBox = new HBox(20, themeLabel, darkRadio, lightRadio);
        themeBox.setAlignment(Pos.CENTER_LEFT);

        // === Change Password ===
        Label passwordLabel = new Label("🔒 New Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter new password");
        passwordField.getStyleClass().add("form-input");

        Button updatePasswordBtn = new Button("✅ Update Password");
        updatePasswordBtn.getStyleClass().add("primary-btn");
        updatePasswordBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password updated!");
            alert.show();
        });

        VBox passwordBox = new VBox(10, passwordLabel, passwordField, updatePasswordBtn);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        // === Delete Account ===
        Button deleteAccountBtn = new Button("🗑 Delete Account");
        deleteAccountBtn.getStyleClass().add("danger-btn");
        deleteAccountBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Account deleted!");
            alert.show();
        });

        // === Version Label ===
        Label versionLabel = new Label("🎵 Musicify v1.0");
        versionLabel.getStyleClass().add("form-subtitle");

        wrapper.getChildren().addAll(
                title,
                emailLabel,
                themeBox,
                passwordBox,
                deleteAccountBtn,
                versionLabel
        );

        return wrapper;
    }
}
