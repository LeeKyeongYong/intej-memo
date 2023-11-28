package com.memo.intejmemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
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
}
