package com.memo.member.member.controller;


import com.memo.member.controller.MemberController;
import com.memo.member.entity.Member;
import com.memo.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET /member/login
    @Test
    @DisplayName("로그인페이지를 보여준다")
    void 로그인페이지보여주기() throws Exception{
        //when
        ResultActions resultActions = mvc
                .perform(get("/member/login"))
                .andDo(print());
        //then
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(view().name("member/member/login"))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("showLogin"))
                    .andExpect(content().string(containsString("""
                            로그인
                            """.stripIndent().trim())))
                    .andExpect(content().string(containsString("""
                            <input type="text" name="username"
                            """.stripIndent().trim())))
                    .andExpect(content().string(containsString("""
                            <input type="password" name="password"
                            """.stripIndent().trim())));
    }

    // POST /member/login
    @Test
    @DisplayName("잘못된 로그인정보")
    void 잘못된로그인정보() throws Exception{
        mvc.perform(
            formLogin("/member/login")
                    .user("username","user1")
                    .password("password","12345")
        )
         .andDo(print())
         .andExpect(unauthenticated());
    }

    // POST /member/login
    @Test
    @DisplayName("로그인처리")
    void 로그인처리()throws Exception{
        mvc.perform(
                    formLogin("/member/login")
                            .user("username","user1")
                            .password("password","1234")
                )
                .andDo(print())
                .andExpect(authenticated());
    }

    // GET /member/login
    @Test
    @DisplayName("회원가입 폼")
    void 회원가입폼() throws Exception{
        //when
            ResultActions resultActions = mvc
                    .perform(get("/member/join"))
                    .andDo(print());
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("member/member/join"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showJoin"))
                .andExpect(content().string(containsString("""
                        회원가입
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <input type="text" name="username"
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <input type="text" name="password"
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <input type="password" name="passwordConfirm"
                        """.stripIndent().trim())));
    }

    // POST /article/write
    @Test
    @DisplayName("회원가입 폼 처리")
    void 회원가입폼처리() throws Exception{
        //WHEN
            ResultActions resultActions = mvc
                    .perform(
                            post("/member/join")
                                .param("username","usernew")
                                .param("password","1234")
                    )
                    .andDo(print());
        //THEN
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(redirectedUrlPattern("/member/login?msg=**"));

        Member member = memberService.findLatest().get();

        assertThat(member.getUsername()).isEqualTo("usernew");
        assertThat(passwordEncoder.matches("1234",member.getPassword())).isTrue();
    }

    // POST /article/write
    @Test
    @DisplayName("중복된 username사용은 가입 실패")
    void 가입실패() throws Exception{

        //when
        ResultActions resultActions = mvc
                .perform(
                        post("/member/join")
                                .with(csrf())
                                .param("username","admin")
                                .param("password","1234")
                )
                .andDo(print());

        //then
        resultActions
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"));
    }
}
