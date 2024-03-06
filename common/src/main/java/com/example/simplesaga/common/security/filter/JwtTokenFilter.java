package com.example.simplesaga.common.security.filter;

import com.example.flights.common.exception.common.JwtAuthenticationException;
import com.example.flights.common.security.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.trace("Process request {} in jwt token filter", request.getRequestId());

        String token = jwtTokenProvider.resolveToken(request);

        log.trace("Resolved token - {}", token);

        try {
            if (Objects.nonNull(token) && !token.isEmpty() && jwtTokenProvider.validateToken(token)) {
                log.trace("Token validated");

                Authentication authentication = jwtTokenProvider.getAuthentication(token);

                if (Objects.nonNull(authentication)) {
                    log.trace("Set authentication {} in context", authentication);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            log.debug("Token validation failed, exception has been thrown - {}, clear context", e.getMessage());

            SecurityContextHolder.clearContext();
        }

        log.trace("Jwt token filter passed for request {}", request.getRequestId());

        filterChain.doFilter(request, response);
    }
}
