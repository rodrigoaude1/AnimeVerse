package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    Optional<Rating> findByUserIdAndAnimeId(Long userId, Long animeId);
    
    Optional<Rating> findByUserIdAndMangaId(Long userId, Long mangaId);
}

