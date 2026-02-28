package com.example.taskapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskWebController {

    @GetMapping("/tasks")
    public String showTasks() {
        
        return "tasks/index"; 
    }
}