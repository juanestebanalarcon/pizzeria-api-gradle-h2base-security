package com.platzi.pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "banana";
    private static Algorithm ALGORITMO = Algorithm.HMAC256(SECRET_KEY);

    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("pizza-api")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITMO);
    }
}
