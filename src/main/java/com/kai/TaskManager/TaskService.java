package com.kai.TaskManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
    public TaskDTO save(TaskDTO taskDTO) {
        Task task = new Task(
        );
        Task savedTask = taskDAO.save(task);
        return new TaskDTO(savedTask.getName(), savedTask.getDescription(),
                savedTask.isCompleted());
    }

    public void delete(String name) {
        taskDAO.delete(name);
    }

    public TaskDTO findByTaskName(String name) {
        Task task = taskDAO.findTaskByName(name);
        if (task != null) {
            return new TaskDTO(task.getName(), task.getDescription(),
                    task.isCompleted());
        }
        return null;
    }

    public List<TaskDTO> findAllTasks() {
        List<Task> tasks = taskDAO.findAllTasks();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO(task.getName(), task.getDescription(),
                    task.isCompleted());
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }

    public void update(TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getName(), taskDTO.getDescription(),
                taskDTO.isCompleted());
        taskDAO.update(task);
    }
}
