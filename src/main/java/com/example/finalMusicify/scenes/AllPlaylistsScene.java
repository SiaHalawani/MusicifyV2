package com.example.finalMusicify.scenes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;

public class AllPlaylistsScene {

    public static Pane createView() {
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(30));
        wrapper.setAlignment(Pos.TOP_CENTER);
        wrapper.getStyleClass().add("glass-card");

        Label title = new Label("📁 Your Playlists");
        title.getStyleClass().add("section-title");

        FlowPane cardGrid = new FlowPane();
        cardGrid.setHgap(20);
        cardGrid.setVgap(20);
        cardGrid.setPadding(new Insets(10));
        cardGrid.setPrefWrapLength(1000);
        cardGrid.setAlignment(Pos.CENTER_LEFT);

        List<PlaylistCard> samplePlaylists = getSamplePlaylists();
        for (PlaylistCard playlist : samplePlaylists) {
            cardGrid.getChildren().add(createPlaylistCard(playlist));
        }

        ScrollPane scrollPane = new ScrollPane(cardGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        wrapper.getChildren().addAll(title, scrollPane);
        return wrapper;
    }

    // === Individual Playlist Card ===
    private static VBox createPlaylistCard(PlaylistCard data) {
        VBox card = new VBox(10);
        card.setPrefSize(200, 150);
        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(15));
        card.getStyleClass().add("playlist-card");

        Label name = new Label("🎵 " + data.name);
        name.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

        Label desc = new Label(data.description);
        desc.setWrapText(true);
        desc.setStyle("-fx-text-fill: #555;");

        Button openBtn = new Button("▶ Open");
        openBtn.getStyleClass().add("primary-btn");
        openBtn.setOnAction(e -> {
            // TODO: Navigate to PlaylistViewScene with this playlist
            System.out.println("Opening playlist: " + data.name);
        });

        card.getChildren().addAll(name, desc, openBtn);
        return card;
    }

    // === Placeholder Sample Data ===
    private static List<PlaylistCard> getSamplePlaylists() {
        List<PlaylistCard> list = new ArrayList<>();
        list.add(new PlaylistCard("Workout Jams", "Energetic songs for your gym sessions"));
        list.add(new PlaylistCard("Chill Vibes", "Relax and unwind"));
        list.add(new PlaylistCard("Throwback Hits", "Best of 90s and 2000s"));
        list.add(new PlaylistCard("Study Playlist", "Focus-enhancing instrumental tracks"));
        list.add(new PlaylistCard("Party Time", "Upbeat tracks to get the party going"));
        return list;
    }

    // === Internal Class for Data ===
    private static class PlaylistCard {
        String name;
        String description;

        PlaylistCard(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
