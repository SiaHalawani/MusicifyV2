//package com.example.finalMusicify.scenes;
//
//import com.example.finalMusicify.utils.ApiService;
//import javafx.beans.property.ReadOnlyStringWrapper;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.collections.transformation.FilteredList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//
//import java.util.Map;
//import java.util.function.Consumer;
//import java.util.function.Function;
//
//public class PlaylistTabScene {
//
//    public static Pane createView(
//            Function<String, Pane> onView,
//            Function<String, Pane> onEdit,
//            Consumer<Consumer<Pane>> onAdd
//    ) {
//        VBox content = new VBox(20);
//        content.setPadding(new Insets(30));
//        content.setAlignment(Pos.TOP_CENTER);
//        content.getStyleClass().add("glass-card");
//
//        Label title = new Label("📁 Your Playlists");
//        title.setFont(new Font("Arial", 22));
//        title.getStyleClass().add("section-title");
//
//        TextField searchField = new TextField();
//        searchField.setPromptText("🔍 Search by name...");
//        searchField.setMaxWidth(300);
//        searchField.getStyleClass().add("form-input");
//
//        Button addBtn = new Button("➕ Add Playlist");
//        addBtn.getStyleClass().add("primary-btn");
//
//        HBox header = new HBox(10, title, new Region(), addBtn);
//        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);
//        header.setAlignment(Pos.CENTER_LEFT);
//
//        TableView<Map<String, Object>> table = new TableView<>();
//        table.getStyleClass().add("table-view");
//
//        TableColumn<Map<String, Object>, String> nameCol = new TableColumn<>("Name");
//        TableColumn<Map<String, Object>, Void> actionsCol = new TableColumn<>("Actions");
//
//        nameCol.setCellValueFactory(data ->
//                new ReadOnlyStringWrapper(String.valueOf(data.getValue().get("name")))
//        );
//
//        actionsCol.setCellFactory(col -> new TableCell<>() {
//            private final Button viewSongsBtn = new Button("🎵");
//            private final Button editBtn = new Button("✏️");
//            private final Button deleteBtn = new Button("🗑");
//
//            {
//                viewSongsBtn.getStyleClass().add("primary-btn");
//                editBtn.getStyleClass().add("secondary-btn");
//                deleteBtn.getStyleClass().add("danger-btn");
//
//                viewSongsBtn.setOnAction(e -> {
//                    Map<String, Object> rowData = getTableView().getItems().get(getIndex());
//                    String playlistId = String.valueOf(rowData.get("id"));
//                    Pane viewPane = onView.apply(playlistId);
//                    addBtn.getScene().setRoot(viewPane);
//                });
//
//                editBtn.setOnAction(e -> {
//                    Map<String, Object> rowData = getTableView().getItems().get(getIndex());
//                    String playlistId = String.valueOf(rowData.get("id"));
//                    Pane editPane = onEdit.apply(playlistId);
//                    addBtn.getScene().setRoot(editPane);
//                });
//
//                deleteBtn.setOnAction(e -> {
//                    Map<String, Object> rowData = getTableView().getItems().get(getIndex());
//                    String playlistId = String.valueOf(rowData.get("id"));
//                    ApiService.delete("/playlists/" + playlistId);
//                    getTableView().getItems().remove(rowData);
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    HBox box = new HBox(5, viewSongsBtn, editBtn, deleteBtn);
//                    box.setAlignment(Pos.CENTER);
//                    setGraphic(box);
//                }
//            }
//        });
//
//        table.getColumns().addAll(nameCol, actionsCol);
//
//        ObservableList<Map<String, Object>> allPlaylists =
//                FXCollections.observableArrayList(ApiService.getList("/playlists"));
//
//        FilteredList<Map<String, Object>> filtered = new FilteredList<>(allPlaylists, p -> true);
//        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
//            filtered.setPredicate(row -> {
//                String name = String.valueOf(row.get("name")).toLowerCase();
//                return name.contains(newVal.toLowerCase().trim());
//            });
//        });
//
//        table.setItems(filtered);
//        table.setPlaceholder(new Label("No playlists found."));
//        table.setPrefHeight(400);
//        VBox.setVgrow(table, Priority.ALWAYS);
//
//        // ✅ Call AddPlaylistScene with callback
//        addBtn.setOnAction(e -> onAdd.accept(newView -> {
//            addBtn.getScene().setRoot(newView);
//        }));
//
//        content.getChildren().addAll(header, searchField, table);
//        return content;
//    }
//}
package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlaylistTabScene {

    public static Pane createView(
            Function<String, Pane> onView,
            Function<String, Pane> onEdit,
            Consumer<Consumer<Pane>> onAdd
    ) {
        VBox content = new VBox(20);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.getStyleClass().add("glass-card");

        Label title = new Label("📁 Your Playlists");
        title.setFont(new Font("Arial", 22));
        title.getStyleClass().add("section-title");

        TextField searchField = new TextField();
        searchField.setPromptText("🔍 Search by name...");
        searchField.setMaxWidth(300);
        searchField.getStyleClass().add("form-input");

        Button addBtn = new Button("➕ Add Playlist");
        addBtn.getStyleClass().add("primary-btn");

        HBox header = new HBox(10, title, new Region(), addBtn);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);
        header.setAlignment(Pos.CENTER_LEFT);

        TableView<Map<String, Object>> table = new TableView<>();
        table.getStyleClass().add("table-view");

        TableColumn<Map<String, Object>, String> nameCol = new TableColumn<>("Name");
        TableColumn<Map<String, Object>, Void> actionsCol = new TableColumn<>("Actions");

        nameCol.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(String.valueOf(data.getValue().get("name")))
        );

        actionsCol.setCellFactory(col -> new TableCell<>() {
            private final Button viewSongsBtn = new Button("🎵");
            private final Button editBtn = new Button("✏️");
            private final Button deleteBtn = new Button("🗑");

            {
                viewSongsBtn.getStyleClass().add("primary-btn");
                editBtn.getStyleClass().add("secondary-btn");
                deleteBtn.getStyleClass().add("danger-btn");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Map<String, Object> rowData = getTableView().getItems().get(getIndex());
                    String playlistId = String.valueOf(rowData.get("id"));

                    viewSongsBtn.setOnAction(e -> {
                        Pane viewPane = onView.apply(playlistId);
                        getScene().lookup(".app-container").lookup(".center-pane").setVisible(true);
                        Consumer<Pane> callback = (Consumer<Pane>) viewPane.getProperties().get("callback");
                        if (callback != null) callback.accept(viewPane);
                    });

                    editBtn.setOnAction(e -> {
                        Pane editPane = onEdit.apply(playlistId);
                        getScene().lookup(".app-container").lookup(".center-pane").setVisible(true);
                        Consumer<Pane> callback = (Consumer<Pane>) editPane.getProperties().get("callback");
                        if (callback != null) callback.accept(editPane);
                    });

                    deleteBtn.setOnAction(e -> {
                        ApiService.delete("/playlists/" + playlistId);
                        getTableView().getItems().remove(rowData);
                    });

                    HBox box = new HBox(5, viewSongsBtn, editBtn, deleteBtn);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        table.getColumns().addAll(nameCol, actionsCol);

        ObservableList<Map<String, Object>> allPlaylists =
                FXCollections.observableArrayList(ApiService.getList("/playlists"));

        FilteredList<Map<String, Object>> filtered = new FilteredList<>(allPlaylists, p -> true);
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filtered.setPredicate(row -> {
                String name = String.valueOf(row.get("name")).toLowerCase();
                return name.contains(newVal.toLowerCase().trim());
            });
        });

        table.setItems(filtered);
        table.setPlaceholder(new Label("No playlists found."));
        table.setPrefHeight(400);
        VBox.setVgrow(table, Priority.ALWAYS);

        addBtn.setOnAction(e -> onAdd.accept(newView -> {
            content.getChildren().setAll(newView);
        }));

        content.getChildren().addAll(header, searchField, table);
        return content;
    }
}