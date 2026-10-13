package com.erickleo.talenthub_api.modules.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                // Desativa o CSRF para esta API que utiliza JWT.
                .csrf(csrf -> csrf.disable())

                // Não utiliza sessão HTTP para manter a autenticação.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Define as rotas públicas e protegidas.
                .authorizeHttpRequests(auth -> auth

                        // Rotas públicas: login e cadastro.
                        .requestMatchers(
                                "/candidato/login",
                                "/empresa/login",
                                "/candidato/criar",
                                "/empresa/criar"
                        ).permitAll()

                        // Apenas candidatos podem se candidatar a vagas.
                        .requestMatchers(
                                HttpMethod.POST,
                                "/candidato/candidatar/**"
                        ).hasRole("CANDIDATE")

                        // Apenas candidatos podem atualizar seus dados.
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/candidato/atualizar/**"
                        ).hasRole("CANDIDATE")

                        // Apenas candidatos podem excluir seus cadastros.
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/candidato/deletar/**"
                        ).hasRole("CANDIDATE")

                        // Apenas empresas podem criar vagas.
                        .requestMatchers(
                                HttpMethod.POST,
                                "/vagas/criar"
                        ).hasRole("COMPANY")

                        // Apenas empresas podem atualizar vagas.
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/vagas/atualizar/**"
                        ).hasRole("COMPANY")

                        // Apenas empresas podem excluir vagas.
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/vagas/deletar/**"
                        ).hasRole("COMPANY")

                        // Candidatos e empresas podem pesquisar vagas.
                        .requestMatchers(
                                HttpMethod.GET,
                                "/vagas/buscar"
                        ).hasAnyRole("CANDIDATE", "COMPANY")

                        // Apenas empresas podem pesquisar candidatos.
                        .requestMatchers(
                                HttpMethod.GET,
                                "/candidato/buscar"
                        ).hasRole("COMPANY")

                        // As demais requisições exigem autenticação.
                        .anyRequest().authenticated()
                )

                // Executa o filtro JWT antes do filtro padrão.
                .addFilterBefore(
                        securityFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
    }