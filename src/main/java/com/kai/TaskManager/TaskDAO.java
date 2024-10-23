package com.kai.TaskManager;

import java.util.List;

public interface TaskDAO {
    Task save(Task Task);
    void delete(String name);
    Task findByName(String name);
    List<Task> findAll();
    void update(Task task);
}
