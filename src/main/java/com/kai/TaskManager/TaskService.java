package com.kai.TaskManager;

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
        Task task = TaskMapper.toEntity(taskDTO);
        Task savedTask = taskDAO.save(task);
        return TaskMapper.toDTO(savedTask);
    }

    public void delete(String name) {
        taskDAO.delete(name);
    }

    public TaskDTO findByTaskName(String name) {
        Task task = taskDAO.findTaskByName(name);

        if (task != null) {
            return TaskMapper.toDTO(task);
        }
        return null;
    }

    public List<TaskDTO> findAllTasks() {
        List<Task> tasks = taskDAO.findAllTasks();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = TaskMapper.toDTO(task);
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }

    public void update(TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        taskDAO.update(task);
    }
}
