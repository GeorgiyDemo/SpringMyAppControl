package com.demenchuk_pi19_4.mycontrol.services;

import com.demenchuk_pi19_4.mycontrol.models.UserModel;
import com.demenchuk_pi19_4.mycontrol.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserRepo userRepo;

    /**
     * Instantiates a new User service.
     *
     * @param userRepo the user repo
     */
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Create.
     *
     * @param client the client
     */
    public void create(UserModel client) {
        userRepo.save(client);
    }

    /**
     * Update user model.
     *
     * @param oldUser the old user
     * @param newUser the new user
     * @return the user model
     */
    public UserModel update(UserModel oldUser, UserModel newUser) {

        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setMiddleName(newUser.getMiddleName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setBirthday(newUser.getBirthday());
        oldUser.setTasks(newUser.getTasks());
        oldUser.setChangedDateTime(newUser.getCreatedDateTime());
        userRepo.save(oldUser);
        return oldUser;
    }

    /**
     * Delete.
     *
     * @param client the client
     */
    public void delete(UserModel client) {
        userRepo.delete(client);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<UserModel> findAll() {
        return userRepo.findAll();
    }

    /**
     * Find optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<UserModel> find(Long id) {
        return userRepo.findById(id);
    }

}