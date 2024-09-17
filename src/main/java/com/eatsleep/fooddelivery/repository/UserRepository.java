package com.eatsleep.fooddelivery.repository;

import com.eatsleep.fooddelivery.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAll();

    // Custom query method to find by ID, excluding soft-deleted users
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isDeleted = false")
    Optional<User> findByIdAndNotDeleted(String id);

    // Custom query methods to find by other attributes, excluding soft-deleted users
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isDeleted = false")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findByEmail(String email);

   // User findById(String id);
}
