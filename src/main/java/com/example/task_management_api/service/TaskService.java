package com.example.task_management_api.service;

import com.example.task_management_api.dto.TaskDto;
import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.User;
import com.example.task_management_api.repository.TaskRepository;
import com.example.task_management_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskDto.TaskResponse createTask(String username, TaskDto.CreateTaskRequest taskRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(Task.TaskStatus.PENDING);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return new TaskDto.TaskResponse(savedTask);
    }

    public List<TaskDto.TaskResponse> getAllUserTasks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Task> tasks = taskRepository.findByUser(user);
        return tasks.stream()
                .map(TaskDto.TaskResponse::new)
                .collect(Collectors.toList());
    }

    public TaskDto.TaskResponse getTaskById(String username, Long taskId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Task task = taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return new TaskDto.TaskResponse(task);
    }

    public TaskDto.TaskResponse updateTask(String username, Long taskId, TaskDto.UpdateTaskRequest updateRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Task task = taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (updateRequest.getTitle() != null) {
            task.setTitle(updateRequest.getTitle());
        }

        if (updateRequest.getDescription() != null) {
            task.setDescription(updateRequest.getDescription());
        }

        if (updateRequest.getStatus() != null) {
            task.setStatus(updateRequest.getStatus());
        }

        Task updatedTask = taskRepository.save(task);
        return new TaskDto.TaskResponse(updatedTask);
    }

    public void deleteTask(String username, Long taskId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Task task = taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }
}
