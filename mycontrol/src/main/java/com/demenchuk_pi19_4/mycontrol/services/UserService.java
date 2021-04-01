package com.demenchuk_pi19_4.mycontrol.services;

import com.demenchuk_pi19_4.mycontrol.models.UserModel;
import com.demenchuk_pi19_4.mycontrol.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void create(UserModel client){
        userRepo.save(client);
    }

    public UserModel update(UserModel oldUser, UserModel newUser){

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

    public void delete(UserModel client){
        userRepo.delete(client);
    }

    public List<UserModel> findAll(){
        return userRepo.findAll();
    }

    public Optional<UserModel> find(Long id){
        return userRepo.findById(id);
    }

}