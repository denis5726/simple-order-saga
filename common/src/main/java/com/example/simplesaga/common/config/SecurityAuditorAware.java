package com.example.simplesaga.common.config;

import com.example.simplesaga.common.security.model.JwtAuthentication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SecurityAuditorAware implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthentication) {
            return Optional.ofNullable(((JwtAuthentication) authentication).getId());
        }
        return Optional.empty();
    }
}
