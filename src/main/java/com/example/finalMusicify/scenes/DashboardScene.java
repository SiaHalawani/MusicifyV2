//package com.example.finalMusicify.scenes;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//import java.util.function.Consumer;
//import java.util.function.Function;
//
//public class DashboardScene {
//
//    public static Scene create(Stage stage) {
//        BorderPane root = new BorderPane();
//        root.getStyleClass().add("app-container");
//
//        VBox sidebar = new VBox(20);
//        sidebar.setPadding(new Insets(30));
//        sidebar.setAlignment(Pos.TOP_LEFT);
//        sidebar.getStyleClass().add("sidebar");
//
//        Label logo = new Label("🎵 MUSICIFY");
//        logo.getStyleClass().add("logo");
//
//        Button homeBtn = new Button("🏠 Home");
//        Button songsBtn = new Button("🎧 Songs");
//        Button addSongBtn = new Button("➕ Add Song");
//        Button playlistsBtn = new Button("📁 Playlists");
//        Button settingsBtn = new Button("⚙ Settings");
//        Button logoutBtn = new Button("🚪 Logout");
//
//        homeBtn.getStyleClass().add("nav-btn");
//        songsBtn.getStyleClass().add("nav-btn");
//        addSongBtn.getStyleClass().add("nav-btn");
//        playlistsBtn.getStyleClass().add("nav-btn");
//        settingsBtn.getStyleClass().add("nav-btn");
//        logoutBtn.getStyleClass().addAll("nav-btn", "logout-btn");
//
//        sidebar.getChildren().addAll(
//                logo, homeBtn, songsBtn, addSongBtn, playlistsBtn, settingsBtn, logoutBtn
//        );
//        root.setLeft(sidebar);
//
//        HBox topBar = new HBox();
//        topBar.setPadding(new Insets(15));
//        topBar.setAlignment(Pos.CENTER_LEFT);
//        topBar.getStyleClass().add("top-bar");
//
//        Label title = new Label("🎶 Welcome to Musicify");
//        title.getStyleClass().add("top-bar-title");
//
//        topBar.getChildren().add(title);
//        root.setTop(topBar);
//
//        StackPane contentArea = new StackPane();
//        contentArea.setPadding(new Insets(0));
//        contentArea.setStyle("-fx-background-color: transparent;");
//        contentArea.getChildren().add(HomeView.create());
//        root.setCenter(contentArea);
//
//        Scene scene = new Scene(root, 1100, 700);
//        scene.getStylesheets().add(DashboardScene.class.getResource("/css/styles.css").toExternalForm());
//        scene.getRoot().getStyleClass().add("dark-mode");
//
//        homeBtn.setOnAction(e -> contentArea.getChildren().setAll(HomeView.create()));
//        songsBtn.setOnAction(e -> contentArea.getChildren().setAll(SongListScene.createView()));
//        addSongBtn.setOnAction(e -> contentArea.getChildren().setAll(AddSongScene.createView()));
//
//        playlistsBtn.setOnAction(e -> contentArea.getChildren().setAll(
//                PlaylistTabScene.createView(
//                        (String playlistId) -> PlaylistViewScene.createView(
//                                playlistId,
//                                () -> playlistsBtn.fire(),
//                                () -> AddSongToPlaylistScene.createView(
//                                        playlistId,
//                                        () -> playlistsBtn.fire()
//                                )
//                        ),
//                        (String playlistId) -> EditPlaylistScene.createView(
//                                playlistId,
//                                () -> playlistsBtn.fire()
//                        ),
//                        (Consumer<Pane> callback) -> AddPlaylistScene.createView(callback)
//                )
//        ));
//
//        settingsBtn.setOnAction(e -> contentArea.getChildren().setAll(SettingsScene.createView(scene)));
//
//        logoutBtn.setOnAction(e -> {
//            stage.setScene(LoginScene.create(stage));
//            stage.centerOnScreen();
//        });
//
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.centerOnScreen();
//
//        return scene;
//    }
//}
package com.example.finalMusicify.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.function.Consumer;
import java.util.function.Function;

public class DashboardScene {

    public static Scene create(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("app-container");

        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setAlignment(Pos.TOP_LEFT);
        sidebar.getStyleClass().add("sidebar");

        Label logo = new Label("🎵 MUSICIFY");
        logo.getStyleClass().add("logo");

        Button homeBtn = new Button("🏠 Home");
        Button songsBtn = new Button("🎧 Songs");
        Button addSongBtn = new Button("➕ Add Song");
        Button playlistsBtn = new Button("📁 Playlists");
        Button settingsBtn = new Button("⚙ Settings");
        Button logoutBtn = new Button("🚪 Logout");

        homeBtn.getStyleClass().add("nav-btn");
        songsBtn.getStyleClass().add("nav-btn");
        addSongBtn.getStyleClass().add("nav-btn");
        playlistsBtn.getStyleClass().add("nav-btn");
        settingsBtn.getStyleClass().add("nav-btn");
        logoutBtn.getStyleClass().addAll("nav-btn", "logout-btn");

        sidebar.getChildren().addAll(
                logo, homeBtn, songsBtn, addSongBtn, playlistsBtn, settingsBtn, logoutBtn
        );
        root.setLeft(sidebar);

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.getStyleClass().add("top-bar");

        Label title = new Label("🎶 Welcome to Musicify");
        title.getStyleClass().add("top-bar-title");

        topBar.getChildren().add(title);
        root.setTop(topBar);

        StackPane contentArea = new StackPane();
        contentArea.setPadding(new Insets(0));
        contentArea.setStyle("-fx-background-color: transparent;");
        contentArea.getChildren().add(HomeView.create());
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 1100, 700);
        scene.getStylesheets().add(DashboardScene.class.getResource("/css/styles.css").toExternalForm());
        scene.getRoot().getStyleClass().add("dark-mode");

        Consumer<Node> setCenterView = node -> contentArea.getChildren().setAll(node);

        homeBtn.setOnAction(e -> setCenterView.accept(HomeView.create()));
        songsBtn.setOnAction(e -> setCenterView.accept(SongListScene.createView()));
        addSongBtn.setOnAction(e -> setCenterView.accept(AddSongScene.createView()));

        playlistsBtn.setOnAction(e -> setCenterView.accept(
                PlaylistTabScene.createView(
                        playlistId -> PlaylistViewScene.createView(
                                playlistId,
                                () -> playlistsBtn.fire(),
                                () -> setCenterView.accept(AddSongToPlaylistScene.createView(
                                        playlistId,
                                        () -> playlistsBtn.fire()
                                ))
                        ),
                        playlistId -> EditPlaylistScene.createView(
                                playlistId,
                                () -> playlistsBtn.fire()
                        ),
                        callback -> AddPlaylistScene.createView(callback)
                )
        ));

        settingsBtn.setOnAction(e -> setCenterView.accept(SettingsScene.createView(scene)));

        logoutBtn.setOnAction(e -> {
            stage.setScene(LoginScene.create(stage));
            stage.centerOnScreen();
        });

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.centerOnScreen();

        return scene;
    }
}
