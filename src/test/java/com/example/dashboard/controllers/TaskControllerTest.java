package com.example.dashboard.controllers;

import com.example.dashboard.models.Task;
import com.example.dashboard.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    static Task task;
    @MockBean
    private TaskService taskService;
    @Autowired
    private MockMvc mockMvc;
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
    @DisplayName("Get all task controller")
    void getAllTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        when(taskService.getAllTasks()).thenReturn(tasks);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/task").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Task find by id controller")
    void getTaskById() throws Exception {
        when(taskService.getTaskById(ArgumentMatchers.anyLong())).thenReturn(task);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/task/"+Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Create task controller")
    void createTask() throws Exception {
        when(taskService.createTask(ArgumentMatchers.any())).thenReturn(task);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/task").content(objectMapper.writeValueAsString(task)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update task controller")
    void updateTask() throws Exception {
        when(taskService.updateTask(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(task);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/task/1" ).content(objectMapper.writeValueAsString(task)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update task controller")
    void deleteTask() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/1" ).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}