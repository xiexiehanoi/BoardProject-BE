package com.yellowstone.boardback.domain.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    public String create(String email){

        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expireDate)
                .compact();

        return jwt;
    }

    public String validate(String jwt){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }
}