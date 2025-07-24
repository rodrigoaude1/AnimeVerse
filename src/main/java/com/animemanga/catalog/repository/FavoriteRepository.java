package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    List<Favorite> findByUserId(Long userId);
    
    Optional<Favorite> findByUserIdAndAnimeId(Long userId, Long animeId);
    
    Optional<Favorite> findByUserIdAndMangaId(Long userId, Long mangaId);
    
    void deleteByUserIdAndAnimeId(Long userId, Long animeId);
    
    void deleteByUserIdAndMangaId(Long userId, Long mangaId);
}

