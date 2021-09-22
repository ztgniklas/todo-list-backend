package com.taigezhang.todolist.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private String id;
    private String content;
    private String username;
    private String time;

    public Task() {
    }

    public Task(String id, String content, String userName, String time) {
        this.id = id;
        this.content = content;
        this.username = userName;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
