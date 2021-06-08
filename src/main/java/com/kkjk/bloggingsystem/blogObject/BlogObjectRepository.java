package com.kkjk.bloggingsystem.blogObject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogObjectRepository extends JpaRepository<BlogObjectEntity, UUID> {
}
