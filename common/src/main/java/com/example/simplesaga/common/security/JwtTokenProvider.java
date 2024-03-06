package com.example.simplesaga.common.security;

import com.example.flights.common.exception.common.JwtAuthenticationException;
import com.example.flights.common.security.model.Permission;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface JwtTokenProvider {

    boolean validateToken(String token) throws JwtAuthenticationException;

    Authentication getAuthentication(String token);

    Long getId(String token);

    Set<Permission> getPermissions(String token);

    String resolveToken(HttpServletRequest request);

}
