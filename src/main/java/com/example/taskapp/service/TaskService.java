package com.example.taskapp.service;

import com.example.taskapp.entity.Task;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 一覧取得
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    // 新規登録（save）
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    // 更新（update）
    public Task update(Long id, Task task) {
      
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません"));
        
        // タイトルと完了状態を上書きして保存
        existingTask.setTitle(task.getTitle());
        
        existingTask.setCompleted(task.isCompleted()); 
        
        return taskRepository.save(existingTask);
    }

    // 削除（delete）
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    //取得
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません"));
    }
}

