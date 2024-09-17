package com.eatsleep.fooddelivery.controllers;


import com.eatsleep.fooddelivery.models.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @PostMapping("add-category")
    public Category addCategory(){
        return null;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return null;
    }

    @GetMapping
    public Category getAllCategories(){
        return null;
    }

    @DeleteMapping("/{id}")
    public Category deleteCategory(@PathVariable Long id){
        return null;
    }
}
