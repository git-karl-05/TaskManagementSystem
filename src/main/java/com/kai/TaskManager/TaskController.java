package com.kai.TaskManager;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTaskDTO = taskService.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
    }

    @GetMapping("/allTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskDTOs = taskService.findAllTasks();
        if (taskDTOs != null) {
            return ResponseEntity.ok(taskDTOs);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<TaskDTO> getTaskByName(@PathVariable String name) {
        TaskDTO taskDTO =  taskService.findByTaskName(name);
        if (taskDTO != null) {
            return ResponseEntity.ok(taskDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.update(taskDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteTask(@PathVariable String name) {
        taskService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
