package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScene {

    public static Scene create(Stage stage) {
        VBox root = new VBox(12);
        root.setPadding(new Insets(40, 50, 40, 50));
        root.getStyleClass().addAll("login-background", "glass-card", "dark-mode");

        Label title = new Label("📝 Register for Musicify");
        title.getStyleClass().add("login-title");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("form-input");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.getStyleClass().add("form-input");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("form-input");

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("form-subtitle");

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("primary-btn");

        registerButton.setOnAction(e -> {
            boolean registered = ApiService.register(usernameField.getText(), passwordField.getText(), emailField.getText());
            if (registered) {
                messageLabel.setText("✅ Registered successfully! Please log in.");
            } else {
                messageLabel.setText("❌ Registration failed.");
            }
        });

        Hyperlink backToLogin = new Hyperlink("Already have an account? Login");
        backToLogin.setOnAction(e -> stage.setScene(LoginScene.create(stage)));

        root.getChildren().addAll(title, usernameField, emailField, passwordField, registerButton, messageLabel, backToLogin);

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(RegisterScene.class.getResource("/css/styles.css").toExternalForm());
        return scene;
    }
}
