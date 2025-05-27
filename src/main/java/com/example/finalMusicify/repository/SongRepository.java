package com.example.finalMusicify.repository;

import com.example.finalMusicify.model.Song;
import com.example.finalMusicify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByTitle(String title);
    List<Song> findByGenre(String genre);
    List<Song> findByMood(String mood);
    List<Song> findByPlaylist(Playlist playlist);
}
