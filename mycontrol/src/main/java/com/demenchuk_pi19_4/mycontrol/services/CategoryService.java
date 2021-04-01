package com.demenchuk_pi19_4.mycontrol.services;

import com.demenchuk_pi19_4.mycontrol.models.CategoryModel;
import com.demenchuk_pi19_4.mycontrol.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Category service.
 */
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    /**
     * Instantiates a new Category service.
     *
     * @param categoryRepo the category repo
     */
    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /**
     * Create.
     *
     * @param category the category
     */
    public void create(CategoryModel category) {
        categoryRepo.save(category);
    }

    /**
     * Update category model.
     *
     * @param oldCategory the old category
     * @param newCategory the new category
     * @return the category model
     */
    public CategoryModel update(CategoryModel oldCategory, CategoryModel newCategory) {

        //Тут можно как-то сделать дженерики, чтоб такого ужаса не было (особенно в UserService)
        //Если просто удаляем-добавляем объект, то меняется id в СУБД (что плохо)
        //Причем это происходит, если даже делаем newUser.setId(oldUser.getId()) для нового объекта сущностей

        oldCategory.setName(newCategory.getName());
        oldCategory.setTasks(newCategory.getTasks());
        oldCategory.setChangedDateTime(newCategory.getCreatedDateTime());
        categoryRepo.save(oldCategory);
        return oldCategory;
    }

    /**
     * Delete.
     *
     * @param category the category
     */
    public void delete(CategoryModel category) {
        categoryRepo.delete(category);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<CategoryModel> findAll() {
        return categoryRepo.findAll();
    }

    /**
     * Find optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<CategoryModel> find(Long id) {
        return categoryRepo.findById(id);
    }

}
