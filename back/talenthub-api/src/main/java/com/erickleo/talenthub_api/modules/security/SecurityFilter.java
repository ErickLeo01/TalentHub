package com.erickleo.talenthub_api.modules.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Pega o valor que veio no cabeçalho Authorization.
        String authHeader = request.getHeader("Authorization");

        // Verifica se existe um token Bearer.
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // Remove "Bearer " e pega somente o token.
            String token = authHeader.substring(7);

            // Verifica se o token é válido.
            boolean tokenValido = jwtProvider.validateToken(token);

            // Se o token for inválido, bloqueia a requisição.
            if (!tokenValido) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Pega o UUID que está dentro do token.
            String userId = jwtProvider.getSubject(token);
            String role = jwtProvider.getRole(token);

            // Cria a autenticação do usuário.
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            java.util.List.of(
                                    new SimpleGrantedAuthority("ROLE_" + role)
                            )
                    );

            // Informa ao Spring Security quem está autenticado.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua a requisição.
        filterChain.doFilter(request, response);
    }
}