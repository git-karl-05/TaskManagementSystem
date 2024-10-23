package com.kai.TaskManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private final TaskDAOImpl taskService;

    @Autowired
    public TaskController(TaskDAOImpl taskService) {
        this.taskService = taskService;
    }

    /*
    * create = POST
    * read = GET
    * update = PUT
    * delete = DELETE
    * */

    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        Task savedTask = taskService.save(task);
        return TaskMapper.toDTO(savedTask);
    }

    @GetMapping("/allTasks")
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        List<TaskDTO> taskDTOs = new ArrayList<>();

        for (Task task : tasks) {
            TaskDTO taskDTO = TaskMapper.toDTO(task);
            taskDTOs.add(taskDTO);
        } return taskDTOs;
    }

    @GetMapping("/{taskName}")
    public Task getTaskByName(@PathVariable String taskName) {
        return taskService.findTaskByName(taskName);
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        taskService.update(task);
    }

    @DeleteMapping("/delete")
    public void deleteTask(@PathVariable String name) {
        taskService.delete(name);
    }

}
