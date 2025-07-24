package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
    
    List<Observation> findByUserId(Long userId);
    
    List<Observation> findByAnimeId(Long animeId);
    
    List<Observation> findByMangaId(Long mangaId);
    
    List<Observation> findByIsPublicTrue();
}

