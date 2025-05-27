package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;
import java.util.Map;

public class PlaylistViewScene {

    public static Pane createView(String playlistId, Runnable onBack, Runnable onAddSong) {
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(30));
        wrapper.setAlignment(Pos.TOP_CENTER);
        wrapper.getStyleClass().add("glass-card");

        Label title = new Label("📂 Playlist Songs");
        title.getStyleClass().add("section-title");

        Button addSongBtn = new Button("➕ Add Song");
        Button removeSongBtn = new Button("🗑 Remove Selected");
        Button backBtn = new Button("↩ Back");

        addSongBtn.getStyleClass().add("primary-btn");
        removeSongBtn.getStyleClass().add("danger-btn");
        backBtn.getStyleClass().add("secondary-btn");

        HBox controls = new HBox(15, addSongBtn, removeSongBtn, backBtn);
        controls.setAlignment(Pos.CENTER_LEFT);

        TableView<SongRow> table = new TableView<>();
        table.setPrefHeight(400);
        table.getStyleClass().add("table-view");

        TableColumn<SongRow, String> titleCol = new TableColumn<>("Title");
        TableColumn<SongRow, String> artistCol = new TableColumn<>("Artist");
        TableColumn<SongRow, String> albumCol = new TableColumn<>("Album");
        TableColumn<SongRow, String> genreCol = new TableColumn<>("Genre");

        titleCol.setCellValueFactory(data -> data.getValue().titleProperty());
        artistCol.setCellValueFactory(data -> data.getValue().artistProperty());
        albumCol.setCellValueFactory(data -> data.getValue().albumProperty());
        genreCol.setCellValueFactory(data -> data.getValue().genreProperty());

        table.getColumns().addAll(titleCol, artistCol, albumCol, genreCol);

        ObservableList<SongRow> rows = FXCollections.observableArrayList();
        List<Map<String, Object>> songs = ApiService.getList("/playlists/" + playlistId + "/songs");
        for (Map<String, Object> song : songs) {
            rows.add(new SongRow(
                    String.valueOf(song.get("title")),
                    String.valueOf(song.get("artist")),
                    String.valueOf(song.get("album")),
                    String.valueOf(song.get("genre"))
            ));
        }
        table.setItems(rows);

        addSongBtn.setOnAction(e -> onAddSong.run());
        backBtn.setOnAction(e -> onBack.run());

        wrapper.getChildren().addAll(title, controls, table);
        return wrapper;
    }
}
