package com.memo.home.home.controller;


import com.memo.intejmemo.domain.home.home.controller.AdmHomeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AdmHomeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("관리자가 아니라면 관리자페이지 접속할수없다")
    @WithUserDetails("user1")
    void 관리자가아니면접속금지() throws Exception{
        //when
        ResultActions resultActions = mvc
                .perform(get("/adm"))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("관리자라면 접속할 수 있다.")
    @WithUserDetails("admin")
    void 관리자접속Ok() throws Exception{
        //when
        ResultActions resultActions = mvc
                .perform(get("/adm"))
                .andDo(print());
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AdmHomeController.class))
                .andExpect(handler().methodName("showMain"))
                .andExpect(view().name("member/adm/main"));
    }

    @Test
    @DisplayName("/member/adm/about")
    void about페이지2() throws Exception{
        //when
            ResultActions resultActions = mvc
                    .perform(get("/member/adm/about"))
                    .andDo(print());
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AdmHomeController.class))
                .andExpect(handler().methodName("showAbout"))
                .andExpect(view().name("/member/adm/about"));
    }
}
