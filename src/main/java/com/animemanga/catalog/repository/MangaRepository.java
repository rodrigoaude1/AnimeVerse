package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    
    Page<Manga> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    @Query("SELECT m FROM Manga m WHERE :genre MEMBER OF m.genres")
    Page<Manga> findByGenre(@Param("genre") String genre, Pageable pageable);
    
    @Query("SELECT m FROM Manga m WHERE m.status = :status")
    Page<Manga> findByStatus(@Param("status") String status, Pageable pageable);
    
    @Query("SELECT m FROM Manga m ORDER BY m.averageRating DESC")
    Page<Manga> findTopRated(Pageable pageable);
    
    @Query("SELECT m FROM Manga m ORDER BY m.createdAt DESC")
    Page<Manga> findRecent(Pageable pageable);
    
    @Query("SELECT DISTINCT g FROM Manga m JOIN m.genres g ORDER BY g")
    List<String> findAllGenres();
}

