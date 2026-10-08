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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Desativa o CSRF porque tá usando uma API REST com JWT.
                .csrf(csrf -> csrf.disable())

                // Não cria sessão para guardar autenticação.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Define as rotas públicas e protegidas.
                .authorizeHttpRequests(auth -> auth

                        // Rotas públicas.
                        .requestMatchers(
                                "/candidato/login",
                                "/empresa/login",
                                "/candidato/criar",
                                "/empresa/criar"
                        ).permitAll()

                        // Apenas candidatos podem se candidatar às vagas.
                        .requestMatchers(
                                HttpMethod.POST,
                                "/candidato/candidatar/**"
                        ).hasRole("CANDIDATE")

                        // Apenas candidatos podem alterar seus dados.
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/candidato/atualizar/**"
                        ).hasRole("CANDIDATE")

                        // Apenas candidatos podem deletar seu cadastro.
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

                        // Apenas empresas podem deletar vagas.
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/vagas/deletar/**"
                        ).hasRole("COMPANY")

                        // Candidatos e empresas podem pesquisar vagas.
                        .requestMatchers(
                                HttpMethod.GET,
                                "/vagas/buscar"
                        ).hasAnyRole("CANDIDATE", "COMPANY")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/candidato/buscar"
                        ).hasRole("COMPANY")

                        // Qualquer outra rota exige autenticação.
                        .anyRequest().authenticated()
                )

                // Coloca o SecurityFilter antes do filtro padrão do Spring.
                .addFilterBefore(
                        securityFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}