package com.example.taskapp.controller;

import com.example.taskapp.entity.Task;
import com.example.taskapp.service.TaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/tasks") // ← "http://localhost:8080/tasks" でアクセスできるようにする
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET /tasks : タスク一覧を返す
    @GetMapping
    public List<Task> getTasks() {
        return service.findAll(); 
    }

    // POST /tasks : タスクを作成する
    @PostMapping
    public Task createTask(@RequestBody @Validated Task task) {
        // @RequestBody : 送られてきたJSONデータをTaskオブジェクトに変換する
        // @Validated : 中身が空じゃないかチェックする
        return service.save(task);
    }
}