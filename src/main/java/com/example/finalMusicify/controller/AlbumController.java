package com.example.finalMusicify.controller;

import com.example.finalMusicify.model.Album;
import com.example.finalMusicify.model.Artist;
import com.example.finalMusicify.repository.AlbumRepository;
import com.example.finalMusicify.repository.ArtistRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumController(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found with ID: " + id));
    }

    @GetMapping("/artist/{artistId}")
    public List<Album> getAlbumsByArtist(@PathVariable int artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found with ID: " + artistId));
        return albumRepository.findByArtist(artist);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable int id) {
        albumRepository.deleteById(id);
    }
}
