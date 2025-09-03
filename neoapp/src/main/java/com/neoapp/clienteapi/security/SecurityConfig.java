package com.neoapp.clienteapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig (JwtProvider jwtProvider, UserDetailsServiceImpl userDetails) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetails;
    }

    @Bean 
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        JwtAuthFilter jwtFilter = new JwtAuthFilter(jwtProvider, userDetailsService);

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/clientes/**").permitAll();
                    .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();

    }
    
    @Bean
    public AuthenticationManager AuthenticationManager (AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


}
