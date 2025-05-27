package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.SongService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class EditSongScene {

    public static Pane createView(Map<String, Object> songData) {
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(30));
        wrapper.setAlignment(Pos.TOP_CENTER);
        wrapper.getStyleClass().add("glass-card");

        Label title = new Label("🎼 Edit Song");
        title.getStyleClass().add("section-title");

        GridPane form = new GridPane();
        form.setHgap(20);
        form.setVgap(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER_LEFT);

        TextField titleField = new TextField(String.valueOf(songData.getOrDefault("title", "")));
        TextField genreField = new TextField(String.valueOf(songData.getOrDefault("genre", "")));
        TextField moodField = new TextField(String.valueOf(songData.getOrDefault("mood", "")));
        TextField filePathField = new TextField(String.valueOf(songData.getOrDefault("filePath", "")));

        CheckBox explicitCheck = new CheckBox("Explicit Lyrics");
        Object explicitObj = songData.get("explicit");
        boolean isExplicit = false;
        if (explicitObj instanceof Boolean) {
            isExplicit = (Boolean) explicitObj;
        } else if (explicitObj instanceof String) {
            isExplicit = Boolean.parseBoolean((String) explicitObj);
        }
        explicitCheck.setSelected(isExplicit);

        form.add(new Label("Title:"), 0, 0);
        form.add(titleField, 1, 0);
        form.add(new Label("Genre:"), 0, 1);
        form.add(genreField, 1, 1);
        form.add(new Label("Mood:"), 0, 2);
        form.add(moodField, 1, 2);
        form.add(new Label("File Path:"), 0, 3);
        form.add(filePathField, 1, 3);
        form.add(new Label("Flags:"), 0, 4);
        form.add(explicitCheck, 1, 4);

        Button saveBtn = new Button("💾 Save Changes");
        saveBtn.getStyleClass().add("primary-btn");

        Label resultLabel = new Label();
        resultLabel.getStyleClass().add("form-subtitle");

        saveBtn.setOnAction(e -> {
            Map<String, String> updatedData = new HashMap<>();
            updatedData.put("title", titleField.getText().trim());
            updatedData.put("genre", genreField.getText().trim());
            updatedData.put("mood", moodField.getText().trim());
            updatedData.put("filePath", filePathField.getText().trim());
            updatedData.put("explicit", String.valueOf(explicitCheck.isSelected()));

            String id;
            try {
                id = String.valueOf(((Number) songData.get("id")).intValue());
            } catch (Exception ex) {
                resultLabel.setText("❌ Invalid song ID");
                return;
            }

            boolean success = SongService.updateSong(id, updatedData);
            resultLabel.setText(success ? "✅ Updated successfully!" : "❌ Failed to update.");
        });

        VBox bottom = new VBox(10, saveBtn, resultLabel);
        bottom.setAlignment(Pos.CENTER_LEFT);

        wrapper.getChildren().addAll(title, form, bottom);
        return wrapper;
    }
}
