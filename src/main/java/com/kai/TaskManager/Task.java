package com.kai.TaskManager;

import java.sql.Timestamp;

public class Task {
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public Task() {}

    public Task(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
