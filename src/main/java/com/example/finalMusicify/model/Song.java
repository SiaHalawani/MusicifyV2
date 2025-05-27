package com.example.finalMusicify.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name = "file_path")
    private String filePath;

    private Boolean explicit;

    private String mood;

    private String genre;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    // ✅ Manual getter for compatibility with isExplicit()
    public boolean isExplicit() {
        return Boolean.TRUE.equals(this.explicit);
    }
}
