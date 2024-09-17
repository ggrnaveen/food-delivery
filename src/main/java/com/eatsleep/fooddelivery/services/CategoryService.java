package com.eatsleep.fooddelivery.services;


import com.eatsleep.fooddelivery.exceptions.ResourceNotFoundException;
import com.eatsleep.fooddelivery.models.Category;
import com.eatsleep.fooddelivery.models.MenuItem;
import com.eatsleep.fooddelivery.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    public List<MenuItem> getMenuItemsByCategoryId(Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return category.getMenuItems();
    }


}
