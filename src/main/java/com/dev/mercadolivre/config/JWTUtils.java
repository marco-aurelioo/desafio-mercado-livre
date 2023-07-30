package com.dev.mercadolivre.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import java.util.Date;

@Component
public class JWTUtils {

    private static final Integer EXPIRATION = 60 * 60 * 24 * 30;

    private static final String SECRET = "secretMaroto";

    public String generateToken(Integer id) {
        return Jwts.builder().setSubject(String.valueOf(id))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    public Integer getSubject(String token) throws AuthenticationException {
        return Integer.parseInt(getClaims(token).getSubject());
    }

    public Boolean validateToken(String token) throws AuthenticationException {
        Claims claims = getClaims(token);
        if(claims.getSubject().isBlank() ||
            claims.getExpiration() == null ||
            claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    private Claims getClaims(String token) throws AuthenticationException {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            throw new AuthenticationException("Token inválido");
        }
    }

}
