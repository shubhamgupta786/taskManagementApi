package com.example.task_management_api.dto;
//package com.example.taskmanagementapi.dto;

import com.example.task_management_api.model.Task;

//import com.example.taskmanagementapi.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class TaskDto {
    
    @Data
    public static class CreateTaskRequest {
        @NotBlank
        private String title;
        
        private String description;
    }

    @Data
    public static class UpdateTaskRequest {
        private String title;
        
        private String description;
        
        private Task.TaskStatus status;
    }

    @Data
    public static class TaskResponse {
        private Long id;
        private String title;
        private String description;
        private Task.TaskStatus status;

        public TaskResponse(Task task) {
            this.id = task.getId();
            this.title = task.getTitle();
            this.description = task.getDescription();
            this.status = task.getStatus();
        }
    }
}