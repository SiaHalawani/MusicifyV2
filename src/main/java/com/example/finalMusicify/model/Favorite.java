package com.example.finalMusicify.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private int id;

    // 👤 User who favorited
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 🎵 Song that was favorited
    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;
}
