package com.kai.TaskManager;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getName(),
                task.getDescription(),
                task.isCompleted()
        );
    }

    public static Task toEntity(TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getName(), taskDTO.getDescription(), taskDTO.isCompleted());
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());
        return task;
    }
}
