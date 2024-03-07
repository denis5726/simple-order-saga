package com.example.simplesaga.user.repository;

import com.example.simplesaga.user.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByIdAndUserId(UUID id, UUID userId);

    @Lock(LockModeType.WRITE)
    Optional<Account> findWithLockByIdAndUserId(UUID id, UUID userId);

    List<Account> findByUserId(UUID userId);

    boolean existsByUserIdAndCurrency(UUID userId, String currency);
}
