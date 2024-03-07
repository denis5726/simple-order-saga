package com.example.simplesaga.user.service.impl;

import com.example.simplesaga.common.security.JwtTokenProvider;
import com.example.simplesaga.user.entity.User;
import com.example.simplesaga.user.service.TokenBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenBuilderImpl implements TokenBuilder {
    private static final long EXPIRATION_IN_SECONDS = 86_400L;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String buildToken(User user) {
        final var now = new Date();
        return Jwts.builder()
                    .setSubject(user.getId().toString())
                    .setClaims(Collections.singletonMap(
                            JwtTokenProvider.PERMISSIONS_CLAIM_KEY, Collections.emptyList() // TODO Add permission assigning
                    ))
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + EXPIRATION_IN_SECONDS * 1000L))
                    .signWith(SignatureAlgorithm.HS256, jwtTokenProvider.getEncodedSecretKey())
                    .compact();
    }
}
