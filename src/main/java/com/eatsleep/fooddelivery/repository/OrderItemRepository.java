package com.eatsleep.fooddelivery.repository;

import com.eatsleep.fooddelivery.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface   OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
