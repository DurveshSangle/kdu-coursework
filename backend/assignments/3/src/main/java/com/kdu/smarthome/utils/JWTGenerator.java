package com.kdu.smarthome.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTGenerator {

    /**
     * Generates a JWT token for the specified username.
     *
     * @param username The username for which the JWT token is generated.
     * @return The generated JWT token.
     */
    public static String generateJWT(String username){
        final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().issuer("kdu").subject("JWT Token")
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key).compact();
    }
}
