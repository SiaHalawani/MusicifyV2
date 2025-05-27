package com.example.finalMusicify.controller;

import com.example.finalMusicify.model.Favorite;
import com.example.finalMusicify.model.Song;
import com.example.finalMusicify.model.User;
import com.example.finalMusicify.repository.FavoriteRepository;
import com.example.finalMusicify.repository.SongRepository;
import com.example.finalMusicify.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public FavoriteController(FavoriteRepository favoriteRepository, UserRepository userRepository, SongRepository songRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @PostMapping
    public Favorite addFavorite(@RequestParam int userId, @RequestParam int songId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setSong(song);
        return favoriteRepository.save(favorite);
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return favoriteRepository.findByUser(user);
    }

    @DeleteMapping
    public void removeFavorite(@RequestParam int userId, @RequestParam int songId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        favoriteRepository.deleteByUserAndSong(user, song);
    }
}
