package com.example.simplesaga.delivery.repository;

import com.example.simplesaga.delivery.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
