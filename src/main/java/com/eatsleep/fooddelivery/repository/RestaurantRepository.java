package com.eatsleep.fooddelivery.repository;

import com.eatsleep.fooddelivery.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
