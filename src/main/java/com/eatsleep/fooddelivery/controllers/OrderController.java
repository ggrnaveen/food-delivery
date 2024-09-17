package com.eatsleep.fooddelivery.controllers;


import com.eatsleep.fooddelivery.dtos.OrderRequest;
import com.eatsleep.fooddelivery.exceptions.ResourceNotFoundException;
import com.eatsleep.fooddelivery.models.Order;
import com.eatsleep.fooddelivery.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody Order orderRequest) throws Exception {
        Order createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) throws ResourceNotFoundException {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/get-orders")
    public ResponseEntity<List<Order>> getOrdersByUser(@RequestParam String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) throws ResourceNotFoundException {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }



}
