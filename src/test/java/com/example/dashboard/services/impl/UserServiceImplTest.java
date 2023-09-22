package com.example.dashboard.services.impl;

import com.example.dashboard.models.User;
import com.example.dashboard.repositories.UserRepository;
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
class UserServiceImplTest {

    static User user;
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setId(Long.valueOf(1));
        user.setName("Name");
    }
    @Test
    @DisplayName("Return all the users")
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(userService.getAllUsers(),users);
    }

    @Test
    @DisplayName("Return all the users throw exception")
    void getAllUsersThrowException() throws Exception {
        when(userRepository.findAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->userService.getAllUsers());
    }

    @Test
    @DisplayName("Return user by id")
    void getUserById() throws Exception {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(user));
        assertEquals(userService.getUserById(Long.valueOf(1)),user);
    }

    @Test
    @DisplayName("Return user by id throw exception")
    void getUserByIdThrowException() throws Exception {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->userService.getUserById(Long.valueOf(1)));
    }

    @Test
    @DisplayName("Create a user")
    void createUser() throws Exception {
        when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);
        assertEquals(userService.createUser(user),user);
    }

    @Test
    @DisplayName("Create a user throw exception")
    void createUserThrowException() throws Exception {
        when(userRepository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->userService.createUser(user));
    }
    @Test
    @DisplayName("Update a user")
    void updateUser() throws Exception {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);
        assertEquals(userService.updateUser(user,Long.valueOf(1)),user);
    }

    @Test
    @DisplayName("Update a user throw exception")
    void updateUserThrowException() throws Exception {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        when(userRepository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->userService.updateUser(user, Long.valueOf(1)));
    }

    @Test
    @DisplayName("Delete a user")
    void deleteUser() throws Exception {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(user));
        userService.deleteUser(Long.valueOf(1));
    }

    @Test
    @DisplayName("Delete a user throw exception")
    void deleteUserThrowException() throws Exception {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,() ->userService.deleteUser(Long.valueOf(1)));
    }
}