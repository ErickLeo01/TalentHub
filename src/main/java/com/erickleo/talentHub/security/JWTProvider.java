package com.erickleo.talentHub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTProvider {

    // Busca a chave secreta definida no application.properties
    // Essa chave será usada para assinar e validar o JWT
    @Value("${security.token.secret}")
    private String secretKey;

    // Método responsável por gerar um token JWT para uma empresa autenticada
    public String generateToken(UUID idCompany) {

        // Cria o algoritmo de assinatura HMAC256 usando a chave secreta
        // O mesmo algoritmo e chave serão usados posteriormente para validar o token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // Cria o JWT e define as informações que serão armazenadas dentro dele
        var token = JWT.create()

                // Subject representa o dono do token
                // Aqui estamos guardando o UUID da empresa autenticada
                .withSubject(idCompany.toString())

                // Issuer identifica quem gerou o token
                .withIssuer("TalentHub")

                // Define o tempo de expiração do token
                // Neste caso, o token será válido por 1 hora (3600 segundos)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))

                // Assina o token usando o algoritmo e a chave secreta
                // Isso garante que o token não seja alterado por terceiros
                .sign(algorithm);

        // Retorna o JWT em formato de String para ser enviado ao cliente
        return token;

    }

    public UUID validateToken(String token) {

        // Cria o algoritmo de validação usando a mesma chave secreta
        // que foi utilizada para gerar o JWT
        Algorithm algorithm = Algorithm.HMAC256(secretKey);


        // Valida o token recebido:
        // - Verifica se a assinatura é válida
        // - Confirma se o token não foi alterado
        // - Decodifica as informações armazenadas dentro do JWT
        var decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);


        // Recupera o subject do token.
        // No nosso caso, o subject guarda o UUID da empresa autenticada.
        // Depois converte a String recebida para o formato UUID.
        return UUID.fromString(decodedJWT.getSubject());

    }
}