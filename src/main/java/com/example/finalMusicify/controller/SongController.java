package com.example.finalMusicify.controller;

import com.example.finalMusicify.model.Song;
import com.example.finalMusicify.model.Playlist;
import com.example.finalMusicify.repository.SongRepository;
import com.example.finalMusicify.repository.PlaylistRepository;
import com.example.finalMusicify.utils.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    public SongController(SongRepository songRepository, PlaylistRepository playlistRepository) {
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    // ✅ Create a new song
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songRepository.save(song);
    }

    // ✅ Get all songs
    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // ✅ Get song by ID
    @GetMapping("/{id}")
    public Song getSongById(@PathVariable int id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with ID: " + id));
    }

    // ✅ Get songs by playlist
    @GetMapping("/playlist/{playlistId}")
    public List<Song> getSongsByPlaylistId(@PathVariable int playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found with ID: " + playlistId));
        return songRepository.findByPlaylist(playlist);
    }

    // ✅ Delete song
    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable int id) {
        songRepository.deleteById(id);
    }

    // ✅ Update existing song
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable int id, @RequestBody Song updated) {
        Song existing = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with ID: " + id));

        existing.setTitle(updated.getTitle());
        existing.setGenre(updated.getGenre());
        existing.setMood(updated.getMood());
        existing.setArtist(updated.getArtist());
        existing.setAlbum(updated.getAlbum());
        existing.setExplicit(updated.isExplicit());

        return songRepository.save(existing);
    }

}
