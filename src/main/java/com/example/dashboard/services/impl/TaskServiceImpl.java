package com.example.dashboard.services.impl;

import com.example.dashboard.models.Task;
import com.example.dashboard.repositories.TaskRepository;
import com.example.dashboard.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        try{
            return taskRepository.findAll();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Task getTaskById(Long id) {
        try{
            Task task = taskRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            return task;
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Task createTask(Task task) {
        try{
            return taskRepository.save(task);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Task updateTask(Task task, Long id) {
        try{
            Task existingTask = taskRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            if(task.getSummary() != null) {
                existingTask.setSummary(task.getSummary());
            } else if (task.getDescription() != null) {
                existingTask.setDescription(task.getDescription());
            } else if ( task.getPriority() != null){
                existingTask.setPriority(task.getPriority());
            } else if ( task.getStatus() != null) {
                existingTask.setStatus(task.getStatus());
            }
            existingTask.setUser_id(task.getUser_id());
            return taskRepository.save(existingTask);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteTask(Long id) {
        try{
            Task task = taskRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            taskRepository.delete(task);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

}