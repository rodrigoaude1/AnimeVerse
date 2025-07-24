package com.animemanga.catalog.repository;

import com.animemanga.catalog.entity.UserListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserListItemRepository extends JpaRepository<UserListItem, Long> {
    
    List<UserListItem> findByUserListId(Long userListId);
}

