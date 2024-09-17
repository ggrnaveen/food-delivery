package com.eatsleep.fooddelivery.controllers;

import com.eatsleep.fooddelivery.models.MenuItem;
import com.eatsleep.fooddelivery.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // 1. Get all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    // 2. Get menu items by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable Long categoryId) {
        List<MenuItem> menuItems = menuService.getMenuItemsByCategory(categoryId);
        return ResponseEntity.ok(menuItems);
    }

    // 3. Add a new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuService.addMenuItem(menuItem);
        return ResponseEntity.ok(createdMenuItem);
    }

    // 4. Update a menu item
    @PutMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItem menuItem) {
        Optional<MenuItem> updatedMenuItem = menuService.updateMenuItem(menuItemId, menuItem);
        return updatedMenuItem.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Soft delete a menu item
    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long menuItemId) {
        boolean isDeleted = menuService.softDeleteMenuItem(menuItemId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
