package com.example.dashboard.controllers;

import com.example.dashboard.models.User;
import com.example.dashboard.services.UserService;
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

@WebMvcTest(UserController.class)
class UserControllerTest {

    static User user;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setId(Long.valueOf(1));
        user.setName("Name");
    }
    @Test
    @DisplayName("Get all users controller")
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("User find by id controller")
    void getUserById() throws Exception {
        when(userService.getUserById(ArgumentMatchers.anyLong())).thenReturn(user);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user/"+Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Create user controller")
    void createUser() throws Exception {
        when(userService.createUser(ArgumentMatchers.any())).thenReturn(user);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/user").content(objectMapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update user controller")
    void updateUser() throws Exception {
        when(userService.updateUser(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(user);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/user/1" ).content(objectMapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update user controller")
    void deleteUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/1" ).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}