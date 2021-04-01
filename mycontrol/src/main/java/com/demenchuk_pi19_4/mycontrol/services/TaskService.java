package com.demenchuk_pi19_4.mycontrol.services;

import com.demenchuk_pi19_4.mycontrol.models.TaskModel;
import com.demenchuk_pi19_4.mycontrol.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }

    public void create(TaskModel task){
        taskRepo.save(task);
    }

    public TaskModel update(TaskModel oldTask, TaskModel newTask){
        oldTask.setTitle(newTask.getDescription());
        oldTask.setDescription(newTask.getDescription());
        oldTask.setCompletedDateTime(newTask.getCompletedDateTime());
        oldTask.setIsReady(newTask.getIsReady());
        oldTask.setCategories(newTask.getCategories());
        oldTask.setUser(newTask.getUser());
        oldTask.setChangedDateTime(newTask.getCreatedDateTime());
        taskRepo.save(oldTask);
        return oldTask;
    }

    public void delete(TaskModel task){
        taskRepo.delete(task);
    }

    public List<TaskModel> findAll(){
        return taskRepo.findAll();
    }

    public Optional<TaskModel> find(Long id){
        return taskRepo.findById(id);
    }

}
