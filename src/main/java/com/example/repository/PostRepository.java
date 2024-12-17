package com.example.repository;

import com.example.entity.Post;
import com.example.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByType(PostType type, Pageable pageable);
    boolean existsByUrl(String url);
} 