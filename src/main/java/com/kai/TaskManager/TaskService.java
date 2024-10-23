package com.kai.TaskManager;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService (TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public TaskDTO save(TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getName(), taskDTO.getDescription(),
                taskDTO.isCompleted());
        Task savedTask = taskDAO.save(task);
        return new TaskDTO(savedTask.getId(), savedTask.getDescription(),
                savedTask.isCompleted());
    }
}
