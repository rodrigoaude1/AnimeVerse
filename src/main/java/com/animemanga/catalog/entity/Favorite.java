package com.animemanga.catalog.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorites", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "anime_id"}),
    @UniqueConstraint(columnNames = {"user_id", "manga_id"})
})
public class Favorite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    private Anime anime;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manga_id")
    private Manga manga;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Favorite() {}
    
    public Favorite(User user, Anime anime) {
        this.user = user;
        this.anime = anime;
    }
    
    public Favorite(User user, Manga manga) {
        this.user = user;
        this.manga = manga;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Anime getAnime() {
        return anime;
    }
    
    public void setAnime(Anime anime) {
        this.anime = anime;
    }
    
    public Manga getManga() {
        return manga;
    }
    
    public void setManga(Manga manga) {
        this.manga = manga;
    }
}

