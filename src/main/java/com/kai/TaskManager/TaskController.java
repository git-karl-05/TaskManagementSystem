package com.kai.TaskManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /*
    * create = POST
    * read = GET
    * update = PUT
    * delete = DELETE
    * */

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @GetMapping("/allTasks")
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/{taskName}")
    public Task getTaskByName(@PathVariable String taskName) {
        return taskService.findTaskByName(taskName);
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody Task task) {
        taskService.update(task);
    }

    @DeleteMapping("/delete")
    public void deleteTask(@PathVariable String name) {
        taskService.delete(name);
    }

}
