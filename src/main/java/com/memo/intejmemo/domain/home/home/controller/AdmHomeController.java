package com.memo.intejmemo.controller;


import com.memo.intejmemo.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdmHomeController {

    private final Rq rq;

    @GetMapping("")
    public String showMain(){
        return "/member/adm/main";
    }

    @GetMapping("/home/main")
    public String showAbout(){
        return "/member/adm/about";
    }
}
