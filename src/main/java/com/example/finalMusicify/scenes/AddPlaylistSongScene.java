package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.SongService;
import com.example.finalMusicify.utils.GlobalState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class AddPlaylistSongScene {

    public static Pane createView(String playlistId, Runnable onBack) {
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(30));
        wrapper.setAlignment(Pos.TOP_CENTER);
        wrapper.getStyleClass().addAll("glass-card", "dark-mode");

        Label title = new Label("➕ Add Song to Playlist");
        title.getStyleClass().add("section-title");

        GridPane form = new GridPane();
        form.setHgap(20);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        TextField titleField = new TextField();
        TextField genreField = new TextField();
        TextField artistField = new TextField();
        TextField albumField = new TextField();
        TextField filePathField = new TextField();
        CheckBox explicitCheck = new CheckBox("Explicit");

        titleField.setPromptText("Song Title");
        genreField.setPromptText("Genre");
        artistField.setPromptText("Artist");
        albumField.setPromptText("Album");
        filePathField.setPromptText("File Path");

        form.add(new Label("Title:"), 0, 0); form.add(titleField, 1, 0);
        form.add(new Label("Genre:"), 0, 1); form.add(genreField, 1, 1);
        form.add(new Label("Artist:"), 0, 2); form.add(artistField, 1, 2);
        form.add(new Label("Album:"), 0, 3); form.add(albumField, 1, 3);
        form.add(new Label("File Path:"), 0, 4); form.add(filePathField, 1, 4);
        form.add(explicitCheck, 1, 5);

        Button submitBtn = new Button("✅ Add Song");
        Button backBtn = new Button("↩ Back");
        Label status = new Label();
        submitBtn.getStyleClass().add("primary-btn");
        backBtn.getStyleClass().add("secondary-btn");
        status.getStyleClass().add("form-subtitle");

        submitBtn.setOnAction(e -> {
            Map<String, String> data = new HashMap<>();
            data.put("title", titleField.getText());
            data.put("genre", genreField.getText());
            data.put("artist", artistField.getText());
            data.put("album", albumField.getText());
            data.put("filePath", filePathField.getText());
            data.put("explicit", String.valueOf(explicitCheck.isSelected()));
            data.put("playlistId", playlistId); // Link to selected playlist
            data.put("userId", String.valueOf(GlobalState.getUserId()));

            boolean success = SongService.addSongToPlaylist(data);
            status.setText(success ? "✅ Added!" : "❌ Failed to add song.");
        });

        backBtn.setOnAction(e -> onBack.run());

        HBox buttons = new HBox(10, submitBtn, backBtn);
        buttons.setAlignment(Pos.CENTER);

        wrapper.getChildren().addAll(title, form, buttons, status);
        return wrapper;
    }
}