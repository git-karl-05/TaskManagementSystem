package com.kai.TaskManager;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId();
                task.getName();
                task.isCompleted()
        );
    }

    public static Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription);
        task.setCompleted(taskDTO.isCompleted());
        return task;
    }
}
