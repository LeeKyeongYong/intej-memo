package com.memo.intejmemo.domain.article.article.service;


import com.memo.intejmemo.domain.article.article.entity.Article;
import com.memo.intejmemo.domain.article.article.repository.ArticleRepository;
import com.memo.intejmemo.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article write(Member author, String title, String body){
        Article article = Article.builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);
        return article;
    }

    public List<Article> findAll(){
        List<Article> articles = articleRepository.findAll();
        Collections.reverse(articles); // 역순으로 정렬
        return articles;
    }

    public Optional<Article> findBId(long id){
        return articleRepository.findById(id);
    }

    @Transactional
    public void delete(Article article){
        articleRepository.delete(article);
    }

    @Transactional
    public void modify(Article article,String title,String body){
        article.setTitle(title);
        article.setBody(body);
    }

    public boolean canModify(Member actor,Article article){
        if(actor==null) return false;
        return article.getAuthor().equals(actor);
    }

    public boolean canDelete(Member actor,Article article){
        if(actor == null) return false;
        if(actor.isAdmin()) return true;
        return article.getAuthor().equals(actor);
    }

    public Optional<Article> findLatest(){
        return articleRepository.findFirstByOrderByIdDesc();
    }
}
