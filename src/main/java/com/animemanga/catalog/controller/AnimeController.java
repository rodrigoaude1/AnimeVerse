package com.animemanga.catalog.controller;

import com.animemanga.catalog.entity.Anime;
import com.animemanga.catalog.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animes")
@CrossOrigin(origins = "*")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping
    public Page<Anime> getAllAnimes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String status) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        if (search != null && !search.isEmpty()) {
            return animeRepository.findByTitleContainingIgnoreCase(search, pageable);
        } else if (genre != null && !genre.isEmpty()) {
            return animeRepository.findByGenre(genre, pageable);
        } else if (status != null && !status.isEmpty()) {
            return animeRepository.findByStatus(status, pageable);
        } else {
            return animeRepository.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        Optional<Anime> anime = animeRepository.findById(id);
        return anime.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top-rated")
    public Page<Anime> getTopRatedAnimes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return animeRepository.findTopRated(pageable);
    }

    @GetMapping("/recent")
    public Page<Anime> getRecentAnimes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return animeRepository.findRecent(pageable);
    }

    @GetMapping("/genres")
    public List<String> getAllGenres() {
        return animeRepository.findAllGenres();
    }

    @PostMapping
    public Anime createAnime(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @RequestBody Anime animeDetails) {
        Optional<Anime> optionalAnime = animeRepository.findById(id);
        
        if (optionalAnime.isPresent()) {
            Anime anime = optionalAnime.get();
            anime.setTitle(animeDetails.getTitle());
            anime.setDescription(animeDetails.getDescription());
            anime.setShortDescription(animeDetails.getShortDescription());
            anime.setCoverImageUrl(animeDetails.getCoverImageUrl());
            anime.setBannerImageUrl(animeDetails.getBannerImageUrl());
            anime.setReleaseDate(animeDetails.getReleaseDate());
            anime.setEndDate(animeDetails.getEndDate());
            anime.setStatus(animeDetails.getStatus());
            anime.setStudio(animeDetails.getStudio());
            anime.setEpisodeCount(animeDetails.getEpisodeCount());
            anime.setGenres(animeDetails.getGenres());
            
            return ResponseEntity.ok(animeRepository.save(anime));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable Long id) {
        if (animeRepository.existsById(id)) {
            animeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

