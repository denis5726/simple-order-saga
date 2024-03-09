package com.example.simplesaga.order.repository;

import com.example.simplesaga.order.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByTitle(String title);

    boolean existsByTitle(String title);
}
