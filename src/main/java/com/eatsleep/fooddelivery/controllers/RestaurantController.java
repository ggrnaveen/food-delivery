package com.eatsleep.fooddelivery.controllers;


import com.eatsleep.fooddelivery.exceptions.ResourceNotFoundException;
import com.eatsleep.fooddelivery.models.MenuItem;
import com.eatsleep.fooddelivery.models.Restaurant;
import com.eatsleep.fooddelivery.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create-restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }


    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.ok(restaurant);
    }


    @GetMapping("/get-all-restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/{restaurantId}/menu-items")
    public ResponseEntity<MenuItem> addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItem menuItem) throws ResourceNotFoundException {
        MenuItem createdMenuItem = restaurantService.addMenuItem(restaurantId, menuItem);
        return ResponseEntity.ok(createdMenuItem);
    }


}
