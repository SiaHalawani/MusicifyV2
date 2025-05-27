package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import com.example.finalMusicify.utils.GlobalState;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class LoginScene {

    public static Scene create(Stage stage) {
        VBox root = new VBox(12);
        root.setPadding(new Insets(40, 50, 40, 50));
        root.getStyleClass().addAll("login-background", "glass-card", "dark-mode");

        Label titleLabel = new Label("🔐 Login to Musicify");
        titleLabel.getStyleClass().add("login-title");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("form-input");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("form-input");

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("error-label");

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("primary-btn");

        Button switchToRegister = new Button("Register");
        switchToRegister.getStyleClass().add("link-btn");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Username and password cannot be empty.");
                return;
            }

            Map<String, Object> user = ApiService.login(username, password);

            if (user != null && user.containsKey("userId")) {
                int userId = ((Number) user.get("userId")).intValue();
                GlobalState.setUserId(userId);

                stage.setScene(DashboardScene.create(stage));
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        });

        switchToRegister.setOnAction(e -> stage.setScene(RegisterScene.create(stage)));

        root.getChildren().addAll(
                titleLabel,
                usernameField,
                passwordField,
                loginButton,
                switchToRegister,
                messageLabel
        );

        Scene scene = new Scene(root, 400, 350);
        scene.getStylesheets().add(LoginScene.class.getResource("/css/styles.css").toExternalForm());
        return scene;
    }
}
