package com.kai.TaskManager;

import java.sql.Timestamp;

public class Task {
    public enum PriorityLevel {
        LOW, MEDIUM, HIGH;
    }
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private PriorityLevel priority;
    private Timestamp dueDate;

    public Task() {}

    public Task(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.priority = null;
        this.dueDate = null;
    }

    public Task(String name, String description, boolean completed, PriorityLevel priority,
                Timestamp dueDate) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.priority = priority;
        this.dueDate = dueDate;
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
    public PriorityLevel getPriority() {
        return this.priority;
    }
    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }
    public Timestamp getDueDate() {
        return this.dueDate;
    }
    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }
}
