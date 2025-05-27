package com.example.finalMusicify.repository;

import com.example.finalMusicify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    // Get all playlists by userId
    List<Playlist> findByUserId(int userId);
}
