package com.example.taskapp.service;

import com.example.taskapp.entity.Task;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // これが必要です

@Service
public class TaskService {

    // ▼ ここで「taskRepository」という名前を決めています
    private final TaskRepository taskRepository;

    // ▼ コンストラクタで中身をもらいます
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 全件取得
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    
    // 保存・更新
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    //検索
    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    //削除
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}