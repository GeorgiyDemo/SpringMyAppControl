package com.demenchuk_pi19_4.mycontrol.controllers;


import com.demenchuk_pi19_4.mycontrol.models.TaskModel;
import com.demenchuk_pi19_4.mycontrol.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Task controller.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Instantiates a new Task controller.
     *
     * @param taskService the task service
     */
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Create task response entity.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskModel item) {
        taskService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Find all tasks response entity.
     *
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<?> findAllTasks() {
        List<TaskModel> itemList = taskService.findAll();
        if (itemList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    /**
     * Find task by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findTaskById(@PathVariable(name = "id") Long id) {
        Optional<TaskModel> currentItem = taskService.find(id);
        if (currentItem.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    /**
     * Update task response entity.
     *
     * @param id      the id
     * @param newTask the new task
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody TaskModel newTask) {
        Optional<TaskModel> currentItemOptional = taskService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        TaskModel oldTask = currentItemOptional.get();
        TaskModel changedTask = taskService.update(oldTask, newTask);
        return new ResponseEntity<>(changedTask, HttpStatus.OK);
    }

    /**
     * Delete task response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id) {
        Optional<TaskModel> currentItemOptional = taskService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        taskService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

