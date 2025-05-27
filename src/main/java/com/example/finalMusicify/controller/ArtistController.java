package com.example.finalMusicify.controller;

import com.example.finalMusicify.model.Artist;
import com.example.finalMusicify.repository.ArtistRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable int id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable int id) {
        artistRepository.deleteById(id);
    }
}
