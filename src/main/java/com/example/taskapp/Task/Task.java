package com.example.taskapp.Task;

public class Task {
    private Integer id;
    private String title;

    //新しいタスクを作るためのコンストラクタ
    public Task(Integer id, String title){
        this.id = id;
        this.title = title;
    }
    
    //データを出し入れするメソッド
    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}    
}
