package com.example.simplesaga.delivery.repository;

import com.example.simplesaga.delivery.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}
