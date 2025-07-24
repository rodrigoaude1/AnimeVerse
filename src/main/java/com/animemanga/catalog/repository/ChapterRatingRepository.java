package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.ChapterRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ChapterRatingRepository extends JpaRepository<ChapterRating, Long> {
    
    Optional<ChapterRating> findByUserIdAndChapterId(Long userId, Long chapterId);
}

