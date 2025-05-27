package com.example.finalMusicify.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "type")
    private String type;

    @Column(name = "favorite")
    private Boolean favorite;

    // ✅ Explicitly add getter to ensure Spring serializes it properly
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
