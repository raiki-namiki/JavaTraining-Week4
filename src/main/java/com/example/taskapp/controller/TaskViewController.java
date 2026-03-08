package com.example.taskapp.controller;

import com.example.taskapp.entity.Task;
import com.example.taskapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    //一覧画面を表示 (GET /tasks)
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/index"; 
    }

    //新規登録画面を表示 (GET /tasks/new)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/form";
    }

    //新規登録を処理する (POST /tasks/new)
    @PostMapping("/new")
    public String createTask(@Validated @ModelAttribute Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        // エラーがあれば、元の入力画面に戻す
        if (result.hasErrors()) {
            return "tasks/form";
        }
        taskService.save(task);
    
        redirectAttributes.addFlashAttribute("successMessage", "タスクを登録しました");
        return "redirect:/tasks";
    }

    //編集画面を表示 (GET /tasks/{id}/edit)
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable ("id") Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "tasks/form";
    }

    //編集を処理する (POST /tasks/{id}/edit)
    @PostMapping("/{id}/edit")
    public String updateTask(@PathVariable ("id") Long id, @Validated @ModelAttribute Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "tasks/form";
        }
        taskService.update(id, task);
        redirectAttributes.addFlashAttribute("successMessage", "タスクを更新しました");
        return "redirect:/tasks";
    }

    //削除を処理する (POST /tasks/{id}/delete)
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable ("id") Long id, RedirectAttributes redirectAttributes) {
        taskService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "タスクを削除しました");
        return "redirect:/tasks";
    }
}