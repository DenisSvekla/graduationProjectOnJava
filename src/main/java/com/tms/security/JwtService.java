package com.tms.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    @Value("${jwt_expire_time}")
    private Integer expireTime;

    @Value("${jwt_key}")
    private String key;

    public String createJwtToken(String login) {
       return Jwts.builder()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(expireTime)))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean isValid(String token) {
     try {
         Jwts.parser()
                 .setSigningKey(key)
                 .parseClaimsJws(token);
         return true;
     } catch (JwtException e) {
         System.out.println("Jwt exception " + e);
     }
     return false;
    }


}
