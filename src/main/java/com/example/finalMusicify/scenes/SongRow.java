package com.example.finalMusicify.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SongRow {
    private final StringProperty title;
    private final StringProperty artist;
    private final StringProperty album;
    private final StringProperty genre;

    public SongRow(String title, String artist, String album, String genre) {
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.genre = new SimpleStringProperty(genre);
    }

    public StringProperty titleProperty() { return title; }
    public StringProperty artistProperty() { return artist; }
    public StringProperty albumProperty() { return album; }
    public StringProperty genreProperty() { return genre; }

    public String getTitle() { return title.get(); }
    public String getArtist() { return artist.get(); }
    public String getAlbum() { return album.get(); }
    public String getGenre() { return genre.get(); }
}
