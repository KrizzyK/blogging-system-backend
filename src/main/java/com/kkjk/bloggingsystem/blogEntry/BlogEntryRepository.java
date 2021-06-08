package com.kkjk.bloggingsystem.blogEntry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogEntryRepository extends JpaRepository<BlogEntryEntity, UUID> {

}
