package com.example.simplesaga.common.security.impl;

import com.example.simplesaga.common.security.exception.JwtAuthenticationException;
import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.common.security.model.Permission;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${simplesaga.jwt.secret-key}")
    private final String secretKey;
    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    private static final long EXPIRATION_IN_SECONDS = 86_400L;
    private static final String PERMISSIONS_CLAIM_KEY = "permissions";
    private final ObjectMapper objectMapper;
    private String encodedSecretKey;

    @PostConstruct
    public void init() {
        encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidToken(String token) {
        try {
            return !Jwts.parser()
                    .setSigningKey(encodedSecretKey)
                    .parseClaimsJws(token)
                    .getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Invalid token");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(ACCESS_TOKEN_HEADER);
    }

  
    public Authentication getAuthentication(String token) {
        return new JwtAuthentication(getId(token), getPermissions(token));
    }

    @SuppressWarnings("unchecked")
    public List<Permission> getPermissions(String token) {
        return (List<String>) Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get(PERMISSIONS_CLAIM_KEY, List.class);
    }
}
