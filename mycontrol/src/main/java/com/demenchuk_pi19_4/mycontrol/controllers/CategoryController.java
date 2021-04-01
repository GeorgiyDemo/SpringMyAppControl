package com.demenchuk_pi19_4.mycontrol.controllers;

import com.demenchuk_pi19_4.mycontrol.models.CategoryModel;
import com.demenchuk_pi19_4.mycontrol.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * The type Category controller.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Instantiates a new Category controller.
     *
     * @param categoryService the category service
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Create category response entity.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel item) {
        categoryService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Find all categories response entity.
     *
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<?> findAllCategories() {
        List<CategoryModel> itemList = categoryService.findAll();
        if (itemList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    /**
     * Find category by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable(name = "id") Long id) {
        Optional<CategoryModel> currentItem = categoryService.find(id);
        if (currentItem.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    /**
     * Update category response entity.
     *
     * @param id          the id
     * @param newCategory the new category
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryModel newCategory) {
        Optional<CategoryModel> currentItemOptional = categoryService.find(id);
        if (currentItemOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CategoryModel oldCategory = currentItemOptional.get();
        CategoryModel changedCategory = categoryService.update(oldCategory, newCategory);
        return new ResponseEntity<>(changedCategory, HttpStatus.OK);
    }

    /**
     * Delete category response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        Optional<CategoryModel> currentItemOptional = categoryService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        categoryService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


