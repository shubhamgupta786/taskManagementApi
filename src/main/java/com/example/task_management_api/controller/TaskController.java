package com.example.task_management_api.controller;

//package com.example.task_management_api.controller;

import com.example.task_management_api.dto.TaskDto;
import com.example.task_management_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto.TaskResponse> createTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TaskDto.CreateTaskRequest taskRequest) {
        
        TaskDto.TaskResponse taskResponse = taskService.createTask(userDetails.getUsername(), taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto.TaskResponse>> getAllTasks(@AuthenticationPrincipal UserDetails userDetails) {
        List<TaskDto.TaskResponse> tasks = taskService.getAllUserTasks(userDetails.getUsername());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto.TaskResponse> getTaskById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        
        TaskDto.TaskResponse task = taskService.getTaskById(userDetails.getUsername(), id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto.TaskResponse> updateTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @Valid @RequestBody TaskDto.UpdateTaskRequest updateRequest) {
        
        TaskDto.TaskResponse taskResponse = taskService.updateTask(userDetails.getUsername(), id, updateRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        
        taskService.deleteTask(userDetails.getUsername(), id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}