package com.example.dashboard.services.impl;

import com.example.dashboard.models.User;
import com.example.dashboard.repositories.UserRepository;
import com.example.dashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        try{
            return userRepository.findAll();
        }catch (Exception exception){
            throw exception;
        }
    }

    @Override
    public User getUserById(Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            return user;
        }catch (Exception exception){
            throw new RuntimeException();
        }
    }

    @Override
    public User createUser(User user) {
        try{
            return userRepository.save(user);
        }catch (Exception exception){
            throw new RuntimeException();
        }
    }

    @Override
    public User updateUser(User user, Long id) {
        try{
            User existingUser = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingUser.setName(user.getName());
            return userRepository.save(existingUser);
        }catch (Exception exception){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteUser(Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            userRepository.delete(user);
        }catch (Exception exception){
            throw new RuntimeException();
        }
    }

}
