package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.EpisodeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EpisodeRatingRepository extends JpaRepository<EpisodeRating, Long> {
    
    Optional<EpisodeRating> findByUserIdAndEpisodeId(Long userId, Long episodeId);
}

