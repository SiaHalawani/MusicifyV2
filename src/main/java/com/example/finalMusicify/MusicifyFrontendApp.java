package com.example.finalMusicify;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.finalMusicify.scenes.LoginScene;

public class MusicifyFrontendApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(LoginScene.create(primaryStage));
        primaryStage.setTitle("Login | Musicify");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
