package com.example.simplesaga.delivery.repository;

import com.example.simplesaga.delivery.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StreetRepository extends JpaRepository<Street, UUID> {
}
