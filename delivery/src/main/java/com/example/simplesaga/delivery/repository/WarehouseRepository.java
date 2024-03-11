package com.example.simplesaga.delivery.repository;

import com.example.simplesaga.delivery.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
}
