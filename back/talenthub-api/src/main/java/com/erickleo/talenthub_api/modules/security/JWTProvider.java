package com.erickleo.talenthub_api.modules.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTProvider {

    private final SecretKey secretKey;

    // Tempo de validade do token: 1 dia (86400000 ms).
    private final long expirationTime = 86400000;

    public JWTProvider(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes;
        try {
            // Tenta decodificar como Base64
            keyBytes = Decoders.BASE64.decode(secret);
        } catch (Exception e) {
            // Caso seja uma string normal no application.properties, converte diretamente para Bytes
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    private String cleanToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7).trim();
        }
        return token != null ? token.trim() : null;
    }

    public String generateToken(UUID userId, String role) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        String formattedRole = role != null ? role.toUpperCase() : "CANDIDATE";

        return Jwts.builder()
                .subject(userId.toString())
                .claim("role", formattedRole)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        String jwt = cleanToken(token);
        if (jwt == null || jwt.isBlank()) {
            return false;
        }

        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            System.err.println("[JWTProvider] Falha ao validar assinaturas/claims do token: " + exception.getMessage());
            return false;
        }
    }

    private Claims getClaims(String token) {
        String jwt = cleanToken(token);
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public String getRole(String token) {
        Object roleClaim = getClaims(token).get("role");
        return roleClaim != null ? roleClaim.toString() : null;
    }
}