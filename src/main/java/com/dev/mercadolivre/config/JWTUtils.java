package com.dev.mercadolivre.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import java.util.Date;

@Component
public class JWTUtils {

    Logger log = org.apache.logging.log4j.LogManager.getLogger();
    private static final Integer EXPIRATION = 999999000;

    private static final String SECRET = "secretMaroto";

    public String generateToken(Integer id) throws AuthenticationException {
        Date date = new Date(System.currentTimeMillis() + EXPIRATION);
        String compactJwts =
             Jwts
                .builder()
                .setSubject(String.valueOf(id))
                .setExpiration(date)
                .signWith( SignatureAlgorithm.HS512, SECRET)
                .compact();
        log.info("generateToken "+compactJwts);
        Claims claims= getClaims(compactJwts);

        claims.getExpiration();


        return compactJwts;
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
            log.info("getClaims "+token);
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e){
            throw new AuthenticationException("Token inválido");
        }
    }

}
