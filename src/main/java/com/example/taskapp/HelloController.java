package com.example.taskapp;

import org.springframework.stereotype.Controller; // ← ここが変わった！
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HelloController {

    @GetMapping("/")
    public String index() {
        // "hello" と返すと、templatesフォルダの中の "hello.html" を探して表示してくれる
        return "hello";
    }
}