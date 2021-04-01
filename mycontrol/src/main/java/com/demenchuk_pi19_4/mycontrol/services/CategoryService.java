package com.demenchuk_pi19_4.mycontrol.services;

import com.demenchuk_pi19_4.mycontrol.models.CategoryModel;
import com.demenchuk_pi19_4.mycontrol.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public void create(CategoryModel category){
        categoryRepo.save(category);
    }

    public CategoryModel update(CategoryModel oldCategory, CategoryModel newCategory){

        //Тут можно как-то сделать дженерики, чтоб такого ужаса не было (особенно в UserService)
        //Если просто удаляем-добавляем объект, то меняется id в СУБД (что плохо)
        //Причем это происходит, если даже делаем newUser.setId(oldUser.getId()) для нового объекта сущностей

        oldCategory.setName(newCategory.getName());
        oldCategory.setTasks(newCategory.getTasks());
        oldCategory.setChangedDateTime(newCategory.getCreatedDateTime());
        categoryRepo.save(oldCategory);
        return oldCategory;
    }

    public void delete(CategoryModel category){
        categoryRepo.delete(category);
    }

    public List<CategoryModel> findAll(){
        return categoryRepo.findAll();
    }

    public Optional<CategoryModel> find(Long id){
        return categoryRepo.findById(id);
    }

}
