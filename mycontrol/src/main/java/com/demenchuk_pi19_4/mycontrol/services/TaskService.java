package com.demenchuk_pi19_4.mycontrol.services;

import com.demenchuk_pi19_4.mycontrol.models.TaskModel;
import com.demenchuk_pi19_4.mycontrol.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Task service.
 */
@Service
public class TaskService {

    private final TaskRepo taskRepo;

    /**
     * Instantiates a new Task service.
     *
     * @param taskRepo the task repo
     */
    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    /**
     * Create.
     *
     * @param task the task
     */
    public void create(TaskModel task) {
        taskRepo.save(task);
    }

    /**
     * Update task model.
     *
     * @param oldTask the old task
     * @param newTask the new task
     * @return the task model
     */
    public TaskModel update(TaskModel oldTask, TaskModel newTask) {
        oldTask.setTitleName(newTask.getTitleName());
        oldTask.setDescription(newTask.getDescription());
        oldTask.setCompletedDateTime(newTask.getCompletedDateTime());
        oldTask.setIsTaskReady(newTask.getIsTaskReady());
        oldTask.setCategories(newTask.getCategories());
        oldTask.setUser(newTask.getUser());
        oldTask.setChangedDateTime(newTask.getCreatedDateTime());
        taskRepo.save(oldTask);
        return oldTask;
    }

    /**
     * Delete.
     *
     * @param task the task
     */
    public void delete(TaskModel task) {
        taskRepo.delete(task);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<TaskModel> findAll() {
        return taskRepo.findAll();
    }

    /**
     * Find optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<TaskModel> find(Long id) {
        return taskRepo.findById(id);
    }

}
