package com.example.service;

import com.example.entity.Post;
import com.example.entity.PostType;
import com.example.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StackOverflowCrawlerService {
    
    private final PostRepository postRepository;
    
    @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    public void crawlStackOverflowDiscussions() {
        try {
            // Stack Overflow의 Questions 페이지를 크롤링
            String url = "https://stackoverflow.com/questions?tab=newest&pagesize=15";
            
            // User-Agent 추가
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();
            
            Elements questions = doc.select("#questions .question-summary");
            
            for (Element question : questions) {
                String title = question.select(".question-hyperlink").text();
                String link = "https://stackoverflow.com" + question.select(".question-hyperlink").attr("href");
                String summary = question.select(".excerpt").text();
                
                // 이미 존재하는 URL인지 확인
                if (!postRepository.existsByUrl(link)) {
                    Post post = Post.builder()
                        .title(title)
                        .url(link)
                        .summary(summary)
                        .postDate(LocalDateTime.now())
                        .type(PostType.STACKOVERFLOW)
                        .build();
                    
                    postRepository.save(post);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 