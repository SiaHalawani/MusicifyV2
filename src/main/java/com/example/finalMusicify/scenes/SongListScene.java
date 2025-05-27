package com.example.finalMusicify.scenes;

import javafx.beans.property.ReadOnlyStringWrapper;
import com.example.finalMusicify.utils.SongService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongListScene {

    public static ScrollPane createView() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.getStyleClass().add("glass-card");

        Label title = new Label("🎧 Your Songs");
        title.setFont(new Font("Arial", 22));
        title.getStyleClass().add("section-title");

        Button addBtn = new Button("➕ Add Song");
        addBtn.getStyleClass().add("primary-btn");

        HBox header = new HBox(10, title, new Region(), addBtn);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);
        header.setAlignment(Pos.CENTER_LEFT);

        TableView<Map<String, String>> table = new TableView<>();
        table.getStyleClass().add("table-view");

        TableColumn<Map<String, String>, String> titleCol = new TableColumn<>("Title");
        TableColumn<Map<String, String>, String> genreCol = new TableColumn<>("Genre");
        TableColumn<Map<String, String>, String> moodCol = new TableColumn<>("Mood");
        TableColumn<Map<String, String>, Void> actionsCol = new TableColumn<>("Actions");

        titleCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get("title")));
        genreCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get("genre")));
        moodCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get("mood")));

        actionsCol.setCellFactory(col -> new TableCell<>() {
            private final Button editBtn = new Button("✏️");
            private final Button deleteBtn = new Button("🗑");

            {
                editBtn.getStyleClass().add("secondary-btn");
                deleteBtn.getStyleClass().add("danger-btn");

                deleteBtn.setOnAction(e -> {
                    Map<String, String> rowData = getTableView().getItems().get(getIndex());
                    SongService.deleteSong(rowData.get("id"));
                    getTableView().getItems().remove(rowData);
                });

                editBtn.setOnAction(e -> {
                    Map<String, String> rowData = getTableView().getItems().get(getIndex());

                    // Convert to Map<String, Object> for EditSongScene
                    Map<String, Object> objectData = new HashMap<>();
                    rowData.forEach(objectData::put);

                    Pane editView = EditSongScene.createView(objectData);
                    content.getChildren().setAll(header, editView);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(5, editBtn, deleteBtn);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        table.getColumns().addAll(titleCol, genreCol, moodCol, actionsCol);

        List<Map<String, String>> fetched = SongService.fetchSongs(); // ✅ as you requested
        ObservableList<Map<String, String>> songs = FXCollections.observableArrayList(fetched);
        table.setItems(songs);
        table.setPlaceholder(new Label("No songs available"));
        table.setPrefHeight(400);
        VBox.setVgrow(table, Priority.ALWAYS);

        addBtn.setOnAction(e -> {
            content.getChildren().setAll(header, AddSongScene.createView());
        });

        content.getChildren().addAll(header, table);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }
}
