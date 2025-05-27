package com.example.finalMusicify.repository;

import com.example.finalMusicify.model.Favorite;
import com.example.finalMusicify.model.User;
import com.example.finalMusicify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndSong(User user, Song song);
    void deleteByUserAndSong(User user, Song song);
}
