package com.neoapp.clienteapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;


    public String gerarToken(String Username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

         return Jwts.builder()
                .setSubject(Username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwSecret)
                .compact();
    }

    public boolean validarToken (String tkn) {
        try{
            Jwts.parser().setSigningKey(jwSecret).parseClaimsJws(tkn);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserJWT (String tkn) {
        return Jwts.parser()
                .setSigningKey(jwSecret)
                .parseClaimsJws(tkn)
                .getBody()
                .getSubject();
    }





}
