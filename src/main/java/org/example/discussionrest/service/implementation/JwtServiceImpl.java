package org.example.discussionrest.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.discussionrest.entity.MyUserDetails;
import org.example.discussionrest.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    @Value("${jwt.signing.algorithm}")
    private String ALGORITHM;

    @Override
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigningKey() {
        return new SecretKeySpec(jwtSigningKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    }

    @Override
    public String generateToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getUser().getId());
        claims.put("role", userDetails.getUser().getRole());
        return generateToken(claims, userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, MyUserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey()).compact();
    }

    @Override
    public boolean isTokenValid(String token, MyUserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
