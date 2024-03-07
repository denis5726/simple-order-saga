package com.example.simplesaga.common.security.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class JwtAuthentication extends UsernamePasswordAuthenticationToken {
    private final UUID id;
    private final Set<Permission> permissions;

    public JwtAuthentication(UUID id, List<Permission> permissions) {
        super(
                id,
                null,
                permissions.stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.name()))
                        .collect(Collectors.toSet())
        );
        this.id = id;
        this.permissions = new HashSet<>(permissions);
    }
}
