package com.example.dashboard.services.impl;

import com.example.dashboard.models.Task;
import com.example.dashboard.repositories.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TaskServiceImplTest {

    static Task task;
    @InjectMocks
    TaskServiceImpl taskService;
    @Mock
    TaskRepository taskRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        task = new Task();
        task.setSummary("Summary");
        task.setDescription("Description");
        task.setStatus("Captured");
        task.setPriority("High");
        task.setUser_id(Long.valueOf(1));
    }

    @Test
    @DisplayName("Return all the Tasks")
    void getAllTask() throws Exception {
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);
        assertEquals(taskService.getAllTasks(),tasks);
    }

    @Test
    @DisplayName("Return all the Tasks throw exception")
    void getAllTasksThrowException() throws Exception {
        when(taskRepository.findAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->taskService.getAllTasks());
    }

    @Test
    @DisplayName("Return Task by id")
    void getTaskById() throws Exception {
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(task));
        assertEquals(taskService.getTaskById(Long.valueOf(1)),task);
    }

    @Test
    @DisplayName("Return Task by id throw exception")
    void getTaskByIdThrowException() throws Exception {
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->taskService.getTaskById(Long.valueOf(1)));
    }

    @Test
    @DisplayName("Create a Task")
    void createTask() throws Exception {
        when(taskRepository.save(ArgumentMatchers.any())).thenReturn(task);
        assertEquals(taskService.createTask(task),task);
    }

    @Test
    @DisplayName("Create a Task throw exception")
    void createTaskThrowException() throws Exception {
        when(taskRepository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->taskService.createTask(task));
    }
    @Test
    @DisplayName("Update a Task")
    void updateTask() throws Exception {
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(task));
        when(taskRepository.save(ArgumentMatchers.any())).thenReturn(task);
        assertEquals(taskService.updateTask(task,Long.valueOf(1)),task);
    }

    @Test
    @DisplayName("Update a Task throw exception")
    void updateTaskThrowException() throws Exception {
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        when(taskRepository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->taskService.updateTask(task, Long.valueOf(1)));
    }

    @Test
    @DisplayName("Delete a Task")
    void deleteTask() throws Exception {
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(task));
        taskService.deleteTask(Long.valueOf(1));
    }

    @Test
    @DisplayName("Delete a user throw exception")
    void deleteTaskThrowException() throws Exception {
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->taskService.deleteTask(Long.valueOf(1)));
    }
}