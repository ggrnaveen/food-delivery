package com.eatsleep.fooddelivery.repository;

import com.eatsleep.fooddelivery.models.Category;
import com.eatsleep.fooddelivery.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    List<MenuItem> findByDeletedFalse();

    List<MenuItem> findByCategoryAndDeletedFalse(Category category);

    @Override
    Optional<MenuItem> findById(Long id);
}
