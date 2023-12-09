package com.memo.intejmemo.domain.home.home.controller;

import com.memo.intejmemo.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final Rq rq;
    @RequestMapping(value = {"/", "index","memoList"} , method = {RequestMethod.GET, RequestMethod.POST})
    public void IndexMemo(HttpServletRequest request, Model model){
        String httpMethod = request.getMethod();

        if ("GET".equals(httpMethod)) {
            // GET 요청 처리
        } else if ("POST".equals(httpMethod)) {
            // POST 요청 처리
        }

        // 공통 처리 로직
    }

    @GetMapping("/")
    public String goToArticleList(String msg){
        return rq.redirect("/article/list",msg);
    }
    
    @GetMapping("/home/session")
    @ResponseBody
    public Map<String,Object> showSession(HttpSession session){
        return Collections.list(session.getAttributeNames()).stream()
                .collect(
                        Collectors.toMap(
                                key->key,
                                key->session.getAttribute(key)
                        )
                );
    }

    @GetMapping("/home/test1")
    @ResponseBody
    public String showTest1(){
        return Thread.currentThread().getName();
    }


    @GetMapping("/home/test2")
    @ResponseBody
    public String showTest2(String name){
        return name+"님 방가방가방가!";
    }

}
