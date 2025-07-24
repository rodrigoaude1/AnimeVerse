package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    
    Page<Chapter> findByMangaIdOrderByChapterNumber(Long mangaId, Pageable pageable);
    
    Chapter findByMangaIdAndChapterNumber(Long mangaId, Integer chapterNumber);
}

