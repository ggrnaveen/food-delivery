package com.eatsleep.fooddelivery.repository;

import com.eatsleep.fooddelivery.models.Payment;
import com.eatsleep.fooddelivery.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
