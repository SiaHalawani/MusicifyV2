package com.example.finalMusicify.scenes;

import com.example.finalMusicify.utils.AlbumService;
import com.example.finalMusicify.utils.ArtistService;
import com.example.finalMusicify.utils.SongService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSongScene {

    public static Pane createView() {
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(30));
        wrapper.setAlignment(Pos.TOP_CENTER);
        wrapper.getStyleClass().addAll("glass-card", "dark-mode");

        Label title = new Label("➕ Add New Song");
        title.getStyleClass().add("section-title");

        GridPane form = new GridPane();
        form.setHgap(40);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        // === Left Column Fields ===
        TextField titleField = new TextField();
        TextField filePathField = new TextField();
        TextField genreField = new TextField();
        TextField moodField = new TextField();
        CheckBox explicitCheck = new CheckBox("Explicit Lyrics");

        for (TextField field : new TextField[]{titleField, filePathField, genreField, moodField}) {
            field.getStyleClass().add("form-input");
        }
        explicitCheck.getStyleClass().add("form-input");

        form.add(new Label("Title:"), 0, 0);
        form.add(titleField, 1, 0);
        form.add(new Label("File Path:"), 0, 1);
        form.add(filePathField, 1, 1);
        form.add(new Label("Genre:"), 0, 2);
        form.add(genreField, 1, 2);
        form.add(new Label("Mood:"), 0, 3);
        form.add(moodField, 1, 3);
        form.add(explicitCheck, 1, 4);

        // === Right Column Dropdowns ===
        ComboBox<String> artistDropdown = new ComboBox<>();
        ComboBox<String> albumDropdown = new ComboBox<>();
        Map<String, String> artistMap = new HashMap<>();
        Map<String, String> albumMap = new HashMap<>();

        artistDropdown.getStyleClass().add("form-input");
        albumDropdown.getStyleClass().add("form-input");

        List<Map<String, String>> artists = ArtistService.fetchArtists();
        for (Map<String, String> artist : artists) {
            String name = artist.get("name");
            String id = artist.get("id");
            artistDropdown.getItems().add(name);
            artistMap.put(name, id);
        }

        List<Map<String, String>> albums = AlbumService.fetchAlbums();
        for (Map<String, String> album : albums) {
            String name = album.get("title");
            String id = album.get("id");
            albumDropdown.getItems().add(name);
            albumMap.put(name, id);
        }

        form.add(new Label("Artist:"), 2, 0);
        form.add(artistDropdown, 3, 0);
        form.add(new Label("Album:"), 2, 1);
        form.add(albumDropdown, 3, 1);

        // === Submit Button ===
        Button submit = new Button("✅ Add Song");
        Label resultLabel = new Label();

        submit.getStyleClass().add("primary-btn");
        resultLabel.getStyleClass().add("form-subtitle");

        submit.setOnAction(e -> {
            Map<String, String> data = new HashMap<>();
            data.put("title", titleField.getText());
            data.put("filePath", filePathField.getText());
            data.put("genre", genreField.getText());
            data.put("mood", moodField.getText());
            data.put("explicit", String.valueOf(explicitCheck.isSelected()));

            String selectedArtist = artistDropdown.getValue();
            String selectedAlbum = albumDropdown.getValue();
            if (selectedArtist != null) data.put("artistId", artistMap.get(selectedArtist));
            if (selectedAlbum != null) data.put("albumId", albumMap.get(selectedAlbum));

            boolean success = SongService.addSong(data);
            resultLabel.setText(success ? "✅ Song added successfully." : "❌ Failed to add song.");
        });

        VBox bottom = new VBox(10, submit, resultLabel);
        bottom.setAlignment(Pos.CENTER_LEFT);

        wrapper.getChildren().addAll(title, form, bottom);
        return wrapper;
    }
}
