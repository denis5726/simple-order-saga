package com.example.simplesaga.delivery.repository;

import com.example.simplesaga.delivery.entity.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, UUID> {
}
