package com.demenchuk_pi19_4.mycontrol.controllers;


import com.demenchuk_pi19_4.mycontrol.models.UserModel;
import com.demenchuk_pi19_4.mycontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create user response entity.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserModel item) {
        userService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Find all users response entity.
     *
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<?> findAllUsers() {
        List<UserModel> itemList = userService.findAll();
        if (itemList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    /**
     * Find user by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable(name = "id") Long id) {
        Optional<UserModel> currentItem = userService.find(id);
        if (currentItem.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    /**
     * Update user response entity.
     *
     * @param id      the id
     * @param newUser the new user
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserModel newUser) {
        Optional<UserModel> currentItemOptional = userService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserModel oldUser = currentItemOptional.get();
        UserModel changedUser = userService.update(oldUser, newUser);
        return new ResponseEntity<>(changedUser, HttpStatus.OK);
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        Optional<UserModel> currentItemOptional = userService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
