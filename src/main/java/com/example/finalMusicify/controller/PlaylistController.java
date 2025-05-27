package com.example.finalMusicify.controller;

import com.example.finalMusicify.model.Playlist;
import com.example.finalMusicify.repository.PlaylistRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistRepository playlistRepository;

    public PlaylistController(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    // ✅ Get all playlists
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // ✅ Get playlists by userId
    @GetMapping("/user/{userId}")
    public List<Playlist> getPlaylistsByUserId(@PathVariable int userId) {
        return playlistRepository.findByUserId(userId);
    }

    // ✅ Create a new playlist
    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    // ✅ Get a playlist by ID
    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable int id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found with ID: " + id));
    }

    // ✅ Delete a playlist
    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable int id) {
        playlistRepository.deleteById(id);
    }

//    // ✅ Update existing playlist
//    @PutMapping("/{id}")
//    public Playlist updatePlaylist(@PathVariable int id, @RequestBody Playlist updated) {
//        Playlist existing = playlistRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Playlist not found with ID: " + id));
//
//        existing.setName(updated.getName());
//        return playlistRepository.save(existing);
//    }
@PutMapping("/{id}")
public Playlist updatePlaylist(@PathVariable int id, @RequestBody Map<String, Object> updates) {
    Playlist existing = playlistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Playlist not found with ID: " + id));

    if (updates.containsKey("name")) {
        existing.setName(String.valueOf(updates.get("name")));
    }

    if (updates.containsKey("userId")) {
        Object userId = updates.get("userId");
        try {
            if (userId instanceof Number) {
                existing.setUserId(((Number) userId).intValue());
            } else {
                existing.setUserId(Integer.parseInt(userId.toString()));
            }
        } catch (Exception e) {
            System.out.println("⚠️ Failed to parse userId: " + userId);
        }
    }

    return playlistRepository.save(existing);
}

}
