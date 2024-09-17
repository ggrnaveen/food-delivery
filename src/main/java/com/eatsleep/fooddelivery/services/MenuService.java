package com.eatsleep.fooddelivery.services;

import com.eatsleep.fooddelivery.models.Category;
import com.eatsleep.fooddelivery.models.MenuItem;
import com.eatsleep.fooddelivery.repository.CategoryRepository;
import com.eatsleep.fooddelivery.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuItemRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Get all menu items
    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findByDeletedFalse();
    }

    // 2. Get menu items by category
    public List<MenuItem> getMenuItemsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(menuRepository::findByCategoryAndDeletedFalse).orElse(null);
    }

    // 3. Add a new menu item
    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    // 4. Update a menu item
    public Optional<MenuItem> updateMenuItem(Long menuItemId, MenuItem updatedMenuItem) {
        Optional<MenuItem> existingMenuItem = menuRepository.findById(menuItemId);
        if (existingMenuItem.isPresent()) {
            MenuItem menuItem = existingMenuItem.get();
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setPrice(updatedMenuItem.getPrice());
            menuItem.setDescription(updatedMenuItem.getDescription());
            menuItem.setCategory(updatedMenuItem.getCategory());
            menuItem.setActive(updatedMenuItem.isActive());
            return Optional.of(menuRepository.save(menuItem));
        }
        return Optional.empty();
    }

    // 5. Soft delete a menu item
    public boolean softDeleteMenuItem(Long menuItemId) {
        Optional<MenuItem> menuItem = menuRepository.findById(menuItemId);
        if (menuItem.isPresent()) {
            MenuItem item = menuItem.get();
            item.setDeleted(true); // Soft delete
            menuRepository.save(item);
            return true;
        }
        return false;
    }
}
