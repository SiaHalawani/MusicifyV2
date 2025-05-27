package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.ApiService;
import com.example.finalMusicify.utils.GlobalState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class EditPlaylistScene {

    public static Pane createView(String playlistId, Runnable onBack) {
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(30));
        wrapper.setAlignment(Pos.TOP_CENTER);
        wrapper.getStyleClass().add("glass-card");

        Label title = new Label("✏️ Edit Playlist");
        title.getStyleClass().add("section-title");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter playlist name");
        nameField.getStyleClass().add("form-input");

        Map<String, Object> playlist = ApiService.getMap("/playlists/" + playlistId);
        if (playlist != null && playlist.containsKey("name")) {
            nameField.setText(String.valueOf(playlist.get("name")));
        }

        Button saveBtn = new Button("💾 Save Changes");
        Button backBtn = new Button("↩ Back");
        saveBtn.getStyleClass().add("primary-btn");
        backBtn.getStyleClass().add("secondary-btn");

        HBox buttons = new HBox(15, saveBtn, backBtn);
        buttons.setAlignment(Pos.CENTER);

        Label status = new Label("");
        status.getStyleClass().add("form-subtitle");

        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                status.setText("Playlist name cannot be empty.");
                return;
            }

            int userId = GlobalState.getUserId(); // ✅ fallback for safety

            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("userId", userId);

            ApiService.put("/playlists/" + playlistId, data);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("✅ Playlist updated successfully!");
            alert.showAndWait();

            onBack.run();
        });

        backBtn.setOnAction(e -> onBack.run());

        wrapper.getChildren().addAll(title, nameField, buttons, status);
        return wrapper;
    }
}
