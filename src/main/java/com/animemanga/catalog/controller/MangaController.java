package com.animemanga.catalog.controller;

import com.animemanga.catalog.entity.Manga;
import com.animemanga.catalog.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mangas")
@CrossOrigin(origins = "*")
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    @GetMapping
    public Page<Manga> getAllMangas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String status) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        if (search != null && !search.isEmpty()) {
            return mangaRepository.findByTitleContainingIgnoreCase(search, pageable);
        } else if (genre != null && !genre.isEmpty()) {
            return mangaRepository.findByGenre(genre, pageable);
        } else if (status != null && !status.isEmpty()) {
            return mangaRepository.findByStatus(status, pageable);
        } else {
            return mangaRepository.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Long id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        return manga.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top-rated")
    public Page<Manga> getTopRatedMangas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mangaRepository.findTopRated(pageable);
    }

    @GetMapping("/recent")
    public Page<Manga> getRecentMangas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mangaRepository.findRecent(pageable);
    }

    @GetMapping("/genres")
    public List<String> getAllGenres() {
        return mangaRepository.findAllGenres();
    }

    @PostMapping
    public Manga createManga(@RequestBody Manga manga) {
        return mangaRepository.save(manga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Long id, @RequestBody Manga mangaDetails) {
        Optional<Manga> optionalManga = mangaRepository.findById(id);
        
        if (optionalManga.isPresent()) {
            Manga manga = optionalManga.get();
            manga.setTitle(mangaDetails.getTitle());
            manga.setDescription(mangaDetails.getDescription());
            manga.setShortDescription(mangaDetails.getShortDescription());
            manga.setCoverImageUrl(mangaDetails.getCoverImageUrl());
            manga.setBannerImageUrl(mangaDetails.getBannerImageUrl());
            manga.setReleaseDate(mangaDetails.getReleaseDate());
            manga.setEndDate(mangaDetails.getEndDate());
            manga.setStatus(mangaDetails.getStatus());
            manga.setAuthor(mangaDetails.getAuthor());
            manga.setPublisher(mangaDetails.getPublisher());
            manga.setChapterCount(mangaDetails.getChapterCount());
            manga.setGenres(mangaDetails.getGenres());
            
            return ResponseEntity.ok(mangaRepository.save(manga));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManga(@PathVariable Long id) {
        if (mangaRepository.existsById(id)) {
            mangaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

