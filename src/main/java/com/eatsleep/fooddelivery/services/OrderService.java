package com.eatsleep.fooddelivery.services;


import com.eatsleep.fooddelivery.exceptions.ResourceNotFoundException;
import com.eatsleep.fooddelivery.models.*;
import com.eatsleep.fooddelivery.repository.MenuItemRepository;
import com.eatsleep.fooddelivery.repository.OrderRepository;
import com.eatsleep.fooddelivery.repository.RestaurantRepository;
import com.eatsleep.fooddelivery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;


    @Transactional
    public Order createOrder(Order orderRequest) throws Exception {

        Double price = 0.0;

        Optional<User> user = userRepository.findById(orderRequest.getUser().getId());

        if(user.isEmpty()){
            throw new Exception();
        }

        Optional<Restaurant> restaurant = restaurantRepository.findById(orderRequest.getRestaurant().getId());


        if(restaurant.isEmpty()){
            throw new Exception();
        }

        List<OrderItem> items = orderRequest.getOrderItems();

        for(OrderItem item:items){
          Optional<MenuItem> menuItem=menuItemRepository.findById(item.getMenuItem().getId());

          if(menuItem.isEmpty()){
              throw  new Exception();
          }
          else {
              price += menuItem.get().getPrice();
          }
        }

        orderRequest.setTotalAmount(price);
        orderRequest.setUpdatedAt(LocalDateTime.now());
        orderRequest.setCreatedAt(LocalDateTime.now());


        orderRequest.setStatus(Status.CONFIRMED);

        return orderRepository.save(orderRequest);
    }

    public Order getOrderById(Long orderId) throws ResourceNotFoundException {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public List<Order> getOrdersByUserId(String userId) {

        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, String status) throws ResourceNotFoundException {
        Order order = getOrderById(orderId);
        order.setStatus(Status.valueOf(status.toUpperCase()));
        return orderRepository.save(order);
    }
}
