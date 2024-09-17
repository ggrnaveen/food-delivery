package com.eatsleep.fooddelivery.services;


import com.eatsleep.fooddelivery.exceptions.ResourceNotFoundException;
import com.eatsleep.fooddelivery.models.MenuItem;
import com.eatsleep.fooddelivery.models.Restaurant;
import com.eatsleep.fooddelivery.repository.MenuItemRepository;
import com.eatsleep.fooddelivery.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;



    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId) throws ResourceNotFoundException {
        Optional<Restaurant> restaurant= restaurantRepository.findById(restaurantId);

        if(restaurant.isEmpty())
            throw new  ResourceNotFoundException("Restaurant not found");
        else return restaurant.get();



        // .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
    }

    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }
    @Transactional
    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) throws ResourceNotFoundException {
        Restaurant restaurant = getRestaurantById(restaurantId);
        menuItem.setRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }
}
