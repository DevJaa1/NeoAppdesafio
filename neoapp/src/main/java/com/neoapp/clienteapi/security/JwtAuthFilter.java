package com.neoapp.clienteapi.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter{

    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl UserDetailsService;


    public JwtAuthFilter(JwtProvider jwtProvider, UserDetailsServiceImpl usedetailsservice ) {
        this.jwtProvider = jwtProvider;
        this.UserDetailsService = usedetailsservice;
    }


    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(header != null && header.startsWith("Bearer")) {
            token = header.substring(7);
            if(jwtProvider.validarToken(token)) {
                username = jwtProvider.getUserJWT(token);
            }
        }

         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = UserDetailsService.loadUserByUsername(username);

            var authentication = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);




    }



}
