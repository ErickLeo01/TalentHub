package com.erickleo.talenthub_api.modules.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim();

            if (!token.isEmpty() && !token.equalsIgnoreCase("null") && !token.equalsIgnoreCase("undefined")) {
                try {
                    String userId = jwtProvider.getSubject(token);

                    if (userId != null && !userId.isBlank()) {
                        String role = null;

                        try {
                            role = jwtProvider.getRole(token);
                        } catch (Exception e) {
                            System.out.println("[SecurityFilter] Não foi possível ler a claim 'role': " + e.getMessage());
                        }

                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                        if (role != null && !role.isBlank()) {
                            String cleanRole = role.replace("ROLE_", "").trim().toUpperCase();
                            authorities.add(new SimpleGrantedAuthority("ROLE_" + cleanRole));
                            authorities.add(new SimpleGrantedAuthority(cleanRole));
                        } else {
                            authorities.add(new SimpleGrantedAuthority("ROLE_CANDIDATE"));
                            authorities.add(new SimpleGrantedAuthority("CANDIDATE"));
                        }

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userId,
                                        null,
                                        authorities
                                );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        SecurityContextHolder.clearContext();
                    }

                } catch (Exception exception) {
                    System.err.println("[SecurityFilter] Falha ao decodificar token: " + exception.getMessage());
                    SecurityContextHolder.clearContext();
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}