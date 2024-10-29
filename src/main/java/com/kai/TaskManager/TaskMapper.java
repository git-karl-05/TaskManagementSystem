package com.kai.TaskManager;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO(
                task.getName(),
                task.getDescription(),
                task.isCompleted(),
                task.getPriority(),
                task.getDueDate()
        );
        taskDTO.setId(task.getId());
        return taskDTO;
    }

    public static Task toEntity(TaskDTO taskDTO) {
        Task task = new Task(
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.isCompleted(),
                taskDTO.getPriority(),
                taskDTO.getDueDate()
        );
        task.setId(taskDTO.getId());
        return task;
    }
}
