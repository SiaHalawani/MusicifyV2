package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class AddPlaylistScene {

    public static void createView(Consumer<Pane> callback) {
        VBox form = new VBox(20);
        form.setPadding(new Insets(30));
        form.setAlignment(Pos.TOP_CENTER);
        form.getStyleClass().add("glass-card");

        Label title = new Label("➕ Add New Playlist");
        title.getStyleClass().add("section-title");

        TextField nameField = new TextField();
        nameField.setPromptText("Playlist name");
        nameField.getStyleClass().add("form-input");

        Button submit = new Button("Create");
        submit.getStyleClass().add("primary-btn");

        submit.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Playlist name cannot be empty.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            HashMap<String, String> data = new HashMap<>();
            data.put("name", name);
            ApiService.post("/playlists", data);

            // After creation, return to updated playlist tab view
            Pane updatedView = PlaylistTabScene.createView(
                    id -> PlaylistViewScene.createView(id, () -> {}, () -> {}),
                    id -> EditPlaylistScene.createView(id, () -> {}),
                    cb -> createView(cb) // reuse self as callback
            );

            callback.accept(updatedView);
        });

        form.getChildren().addAll(title, nameField, submit);
        callback.accept(form);
    }
}
