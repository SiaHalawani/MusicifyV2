
package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

public class SongPickerScene {

    public static Pane createView(List<String> availableSongs, Callback<String> onSongSelected) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("glass-card");

        Label title = new Label("🎵 Pick a Song");
        title.getStyleClass().add("section-title");

        ListView<String> songList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(availableSongs);
        songList.setItems(items);
        songList.setPrefHeight(300);
        songList.setPrefWidth(400);

        Button selectBtn = new Button("✔ Select Song");
        selectBtn.getStyleClass().add("primary-btn");

        selectBtn.setOnAction(e -> {
            String selected = songList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                onSongSelected.call(selected);
            }
        });

        root.getChildren().addAll(title, songList, selectBtn);
        return root;
    }

    // Simple functional interface for callback
    public interface Callback<T> {
        void call(T data);
    }
}
