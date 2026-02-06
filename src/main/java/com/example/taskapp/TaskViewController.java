package com.example.taskapp;

import org.springframework.stereotype.Controller;
import com.example.taskapp.entity.Task;
import com.example.taskapp.entity.form.TaskForm;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.taskapp.service.TaskService;


import java.util.List;


@Controller 
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    //serviceの呼び出し
    public TaskViewController(TaskService taskService){
        this.taskService = taskService;
    }

    //一覧表示
    @GetMapping
    public String index(Model model) {

        //データベースからタスク一覧を取得
        List<Task> tasks = taskService.findAll();

        //Modelへリストを渡す
        model.addAttribute("tasks", tasks);

        //表示するHTMLファイルの名前を返す
        return "tasks/index";
    }

    //新規作成画面の表示
    @GetMapping("/new")
    public String showCreationForm(@ModelAttribute TaskForm taskForm){

        return "tasks/new";
    }
    
    //登録ボタンが押されたとき
    @PostMapping
    public String create(
        @Validated @ModelAttribute TaskForm taskForm, //入力チェック
        BindingResult bindingResult //結果

        
    ){
        //エラーの場合
        if (bindingResult.hasErrors()){
            return "tasks/new"; //保存せずに入力画面へ戻る
        }

    //エラーがない場合
    Task task = new Task();
    task.setTitle(taskForm.getTitle());
    task.setCompleted(false); //初期値：未完了

    taskService.save(task);

    return "redirect:/tasks";

    }

    // 編集画面の表示
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        // IDでタスクを検索
        Task task = taskService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        
        TaskForm taskForm = new TaskForm();
        taskForm.setTitle(task.getTitle());
        
        model.addAttribute("taskForm", taskForm);
        model.addAttribute("taskId", id); // 更新用URLのためにIDも渡す
        
        return "tasks/edit"; // edit.htmlを表示
    }

    // 更新処理
    @PostMapping("/{id}")
    public String update(
        @PathVariable Integer id,
        @Validated @ModelAttribute TaskForm taskForm,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes // フラッシュメッセージ用
    ) {
        if (bindingResult.hasErrors()) {
            return "tasks/edit";
        }

        Task task = taskService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        task.setTitle(taskForm.getTitle());
        taskService.save(task); // 更新

        // フラッシュメッセージ
        redirectAttributes.addFlashAttribute("success", "タスクを更新しました");

        return "redirect:/tasks";
    }

    // 削除処理
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Task task = taskService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taskService.delete(task); 

        redirectAttributes.addFlashAttribute("success", "タスクを削除しました");
        
        return "redirect:/tasks";
    }

}