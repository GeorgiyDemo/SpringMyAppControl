package com.demenchuk_pi19_4.mycontrol.controllers;


import com.demenchuk_pi19_4.mycontrol.models.CategoryModel;
import com.demenchuk_pi19_4.mycontrol.models.TaskModel;
import com.demenchuk_pi19_4.mycontrol.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskModel item) {
        taskService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> findAllTasks() {
        List<TaskModel> itemList = taskService.findAll();
        if (itemList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTaskById(@PathVariable(name = "id") Long id) {
        Optional<TaskModel> currentItem = taskService.find(id);
        if (currentItem.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody TaskModel newTask) {
        Optional<TaskModel> currentItemOptional = taskService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        TaskModel oldTask = currentItemOptional.get();
        TaskModel changedTask = taskService.update(oldTask, newTask);
        return new ResponseEntity<>(changedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id) {
        Optional<TaskModel> currentItemOptional = taskService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        taskService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

