package com.memo.article.article.controller;

import com.memo.intejmemo.domain.controller.ArticleController;
import com.memo.intejmemo.domain.entity.Article;
import com.memo.intejmemo.domain.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ArticleService articleService;


    //Get /article/list
    @Test
    @DisplayName("게시물 목록 페이지를 보여주기.")
    void t1() throws Exception{

        //WHEN
        ResultActions resultActions = mvc
                .perform(get("/article/list"))
                .andDo(print());

        Article article =articleService.findLatest().get();

        //THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(ArticleController.class))
                .andExpect(handler().methodName("showList"))
                .andExpect(content().string(containsString("""
                        게시글 목록 """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        %d번 : %s
                        """.formatted(article.getId(),article.getTitle().stripIndent().trim()))));
    }


    //Get /article/detail{id}

}
