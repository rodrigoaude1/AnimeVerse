package com.animemanga.catalog.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_list_items", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_list_id", "anime_id"}),
    @UniqueConstraint(columnNames = {"user_list_id", "manga_id"})
})
public class UserListItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "added_at")
    private LocalDateTime addedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_list_id")
    private UserList userList;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    private Anime anime;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manga_id")
    private Manga manga;
    
    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now();
    }
    
    // Constructors
    public UserListItem() {}
    
    public UserListItem(UserList userList, Anime anime) {
        this.userList = userList;
        this.anime = anime;
    }
    
    public UserListItem(UserList userList, Manga manga) {
        this.userList = userList;
        this.manga = manga;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
    
    public UserList getUserList() {
        return userList;
    }
    
    public void setUserList(UserList userList) {
        this.userList = userList;
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

