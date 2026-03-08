package com.example.taskapp.controller;

import com.example.taskapp.entity.Task;
import com.example.taskapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ① 一覧取得 (GET /api/tasks)
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    // ② 新規登録 (POST /api/tasks)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody @Validated Task task) {
        return taskService.save(task);
    }

    
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody @Validated Task task) {
    
        return taskService.update(id, task);
    }

    // ④ 削除 (DELETE /api/tasks/{id})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}