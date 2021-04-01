package com.demenchuk_pi19_4.mycontrol.controllers;

import com.demenchuk_pi19_4.mycontrol.models.CategoryModel;
import com.demenchuk_pi19_4.mycontrol.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel item) {
        categoryService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> findAllCategories() {
        List<CategoryModel> itemList = categoryService.findAll();
        if (itemList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable(name = "id") Long id) {
        Optional<CategoryModel> currentItem = categoryService.find(id);
        if (currentItem.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(currentItem.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryModel newCategory) {
        Optional<CategoryModel> currentItemOptional = categoryService.find(id);
        if (currentItemOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CategoryModel oldCategory = currentItemOptional.get();
        CategoryModel changedCategory = categoryService.update(oldCategory, newCategory);
        return new ResponseEntity<>(changedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        Optional<CategoryModel> currentItemOptional = categoryService.find(id);
        if (currentItemOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        categoryService.delete(currentItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


