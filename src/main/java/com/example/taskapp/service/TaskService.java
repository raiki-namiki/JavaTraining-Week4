package com.example.taskapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.taskapp.entity.Task;
import com.example.taskapp.repository.TaskRepository;
import com.example.taskapp.exception.TaskNotFoundException;

@Service
@Transactional
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // タスクを全部持ってくる
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    // タスクを保存・登録する
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    // タスクを更新する
    public Task update(Long id, Task taskDetails) {
        // IDで探して、なければエラーを出す
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        
        // 中身を書き換える
        task.setTitle(taskDetails.getTitle());
        task.setCompleted(taskDetails.isCompleted());
        
        // 保存し直す
        return taskRepository.save(task);
    }

    // タスクを削除する
    public void delete(Long id) {
        // 存在確認してから消す
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}