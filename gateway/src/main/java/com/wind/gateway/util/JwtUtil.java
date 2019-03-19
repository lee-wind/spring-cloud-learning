package com.wind.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    private static final String secret = "wind";
    private static final long intervalMillis = 2 * 60 * 60 * 1000;
    //private static final long intervalMillis = 1000;

    public boolean auth(String jwt, String userId){

        return userId.equals(getUserId(jwt)) && !isExpired(jwt);
    }

    public static String create(Map<String, Object> claims){

        Date expirationDate = new Date(System.currentTimeMillis() + intervalMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static boolean isExpired(String jwt){

        Claims claims = getClaims(jwt);
        Date expiration = claims.getExpiration();

        return expiration.before(new Date());
    }

    public static String refresh(String jwt){

        Claims claims = getClaims(jwt);
        claims.put("created", new Date());

        return create(claims);
    }

    public static Claims getClaims(String jwt){

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static String getUserId(String jwt) {

        return getClaims(jwt).getSubject();
    }
}

