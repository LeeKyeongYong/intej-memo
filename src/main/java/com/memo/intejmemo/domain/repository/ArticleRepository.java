package com.memo.intejmemo.domain.repository;

import com.memo.intejmemo.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    Optional<Article> findFirstByOrderByIdDesc();
}
