package com.example.hellospring.Task;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    //データを保存するリスト
    private final List<Task> taskList = new ArrayList<>();

    //IDを管理するための変数
    private int nextId = 1;

    //確認用
    public TaskService(){
        taskList.add(new Task(nextId++, "buy milk"));
        taskList.add(new Task(nextId++, "read book"));
    }

    // 全タスクを取得
    public List<Task> findAll() {
        return taskList;
    }

    public Task create(String title) {
        Task newTask = new Task(nextId++, title);
        taskList.add(newTask);
        return newTask;
    }
    
}
