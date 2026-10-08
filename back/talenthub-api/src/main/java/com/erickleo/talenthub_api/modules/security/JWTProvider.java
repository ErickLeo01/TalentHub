package com.erickleo.talenthub_api.modules.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTProvider {

    private final SecretKey secretKey;

    // Tempo de validade do token: 1 dia.
    private final long expirationTime = 86400000;

    public JWTProvider(@Value("${jwt.secret}") String secret) {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UUID userId, String role) {

        Date now = new Date();

        Date expirationDate =
                new Date(now.getTime() + expirationTime);

        return Jwts.builder()

                // Identifica o usuário dentro do token.
                .subject(userId.toString())

                // Identifica se é CANDIDATE ou COMPANY.
                .claim("role", role)

                // Informa quando o token foi criado.
                .issuedAt(now)

                // Informa quando o token irá expirar.
                .expiration(expirationDate)

                // Assina o token usando a chave secreta.
                .signWith(secretKey)

                .compact();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {

            return false;
        }
    }

    public String getSubject(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getRole(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}