package com.example.simplesaga.common.security;

import com.example.simplesaga.common.security.exception.JwtAuthenticationException;
import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.common.security.model.Permission;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${simplesaga.jwt.secret-key}")
    private final String secretKey;
    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    public static final String PERMISSIONS_CLAIM_KEY = "permissions";
    @Getter
    private String encodedSecretKey;

    @PostConstruct
    public void init() {
        encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidToken(String token) {
        try {
            return !parseClaims(token).getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Invalid token");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(ACCESS_TOKEN_HEADER);
    }

    @SuppressWarnings("unchecked")
    public JwtAuthentication getAuthentication(String token) {
        final var claims = parseClaims(token);
        return new JwtAuthentication(
                UUID.fromString(claims.getSubject()),
                (List<Permission>) claims.get(PERMISSIONS_CLAIM_KEY, List.class)
        );
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
