package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BlogEntryRepository extends JpaRepository<BlogEntryEntity, UUID> {

    List<BlogEntryEntity> findAllByAuthor(UserEntity userEntity);
}
