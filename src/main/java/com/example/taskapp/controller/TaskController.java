package com.example.taskapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.taskapp.Task.Task;
import com.example.taskapp.Task.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks") 
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET /api/tasks : 一覧取得
    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    // POST /api/tasks : タスク作成
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Map<String, String> request) {
        String title = request.get("title");

        // タイトルが空ならエラー(400)を返す
        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Title is required");
        }

        Task createdTask = taskService.create(title);
        return ResponseEntity.ok(createdTask);
    }
}