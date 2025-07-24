package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {
    
    List<UserList> findByUserId(Long userId);
    
    List<UserList> findByIsPublicTrue();
}

