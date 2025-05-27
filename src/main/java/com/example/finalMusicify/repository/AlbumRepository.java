package com.example.finalMusicify.repository;

import com.example.finalMusicify.model.Album;
import com.example.finalMusicify.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByArtist(Artist artist);
}
