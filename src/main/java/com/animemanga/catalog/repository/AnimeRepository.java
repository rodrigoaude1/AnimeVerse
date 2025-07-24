package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    
    Page<Anime> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    @Query("SELECT a FROM Anime a WHERE :genre MEMBER OF a.genres")
    Page<Anime> findByGenre(@Param("genre") String genre, Pageable pageable);
    
    @Query("SELECT a FROM Anime a WHERE a.status = :status")
    Page<Anime> findByStatus(@Param("status") String status, Pageable pageable);
    
    @Query("SELECT a FROM Anime a ORDER BY a.averageRating DESC")
    Page<Anime> findTopRated(Pageable pageable);
    
    @Query("SELECT a FROM Anime a ORDER BY a.createdAt DESC")
    Page<Anime> findRecent(Pageable pageable);
    
    @Query("SELECT DISTINCT g FROM Anime a JOIN a.genres g ORDER BY g")
    List<String> findAllGenres();
}

