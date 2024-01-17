package com.example.empowerprobackend.utils;
import com.example.empowerprobackend.models.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    private static final String secretKey = "secret";
    private static final long expiryDuration = 24 * 60 * 60 * 1000; //24h

    public String generateAccessToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(map)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiryDuration))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Json Web token hase expired", ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or has only whitespace", ex);
        } catch (MalformedJwtException ex) {
            LOGGER.error("Json Web Token is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Json web Token is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("The signature validation failed", ex);
        }
        return false;
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        return ((List<String>) getClaims(token).get("roles")).stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}


