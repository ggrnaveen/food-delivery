package com.eatsleep.fooddelivery.controllers;


import com.eatsleep.fooddelivery.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
}
