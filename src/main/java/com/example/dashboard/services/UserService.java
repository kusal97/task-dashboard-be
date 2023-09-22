package com.example.dashboard.services;

import com.example.dashboard.models.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(@RequestBody User user);

    User updateUser(@RequestBody User user, Long id);

    void deleteUser(Long id);

}
