package org.example.discussionrest.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    @Value("${jwt.signing.algorithm}")
    private String ALGORITHM;

    @Override
    public int extractId(String token) {
        return Integer.parseInt(extractClaim(token, Claims::getSubject));
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
    public TokenReadDto generateToken(User user) {
        return new TokenReadDto(createToken(String.valueOf(user.getId())));
    }

    private String createToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey()).compact();
    }

    @Override
    public boolean isTokenInvalid(String token) {
        return isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
