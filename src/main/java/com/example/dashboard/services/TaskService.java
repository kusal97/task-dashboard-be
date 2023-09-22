package com.example.dashboard.services;

import com.example.dashboard.models.Task;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(@RequestBody Task task);

    Task updateTask(@RequestBody Task task, Long id);

    void deleteTask(Long id);

}