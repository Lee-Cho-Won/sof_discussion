package com.example.controller;

import com.example.entity.Post;
import com.example.entity.PostType;
import com.example.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public String listPosts(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Post> posts = postRepository.findAllByType(PostType.NORMAL, pageable);
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    @GetMapping("/stackoverflow")
    public String listStackOverflowPosts(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Post> posts = postRepository.findAllByType(PostType.STACKOVERFLOW, pageable);
        model.addAttribute("posts", posts);
        return "posts/stackoverflow";
    }
} 