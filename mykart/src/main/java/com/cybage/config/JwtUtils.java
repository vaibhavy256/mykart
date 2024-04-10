package com.cybage.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {

    @Value("${app.secret}")
    private String SECRET_KEY;



    public boolean validateToken(String token, String userName) {
        String tokenUserName = getUsername(token);
        return (userName.equals(tokenUserName) && !isTokenExp(token));
    }


    public boolean isTokenExp(String Token) {
        Date expDate = getExpDate(Token);
        return expDate.before(new Date(System.currentTimeMillis()));
    }

    public String getUsername(String Token) {
        return getClaims(Token).getSubject();
    }

    public Date getExpDate(String Token) {
        return getClaims(Token).getExpiration();
    }

    public Claims getClaims(String token) {
        System.out.println(SECRET_KEY);
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateToken(String subject) {
        System.out.println(subject + "***");
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("vaibhav")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }
//public String generateToken(String userName) {
//    Map<String, Object> claims = new HashMap<>();
//    return createToken(claims, userName);
//}
//
//    private String createToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//    }

}

