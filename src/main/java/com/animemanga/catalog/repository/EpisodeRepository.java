package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    
    Page<Episode> findByAnimeIdOrderByEpisodeNumber(Long animeId, Pageable pageable);
    
    Episode findByAnimeIdAndEpisodeNumber(Long animeId, Integer episodeNumber);
}

