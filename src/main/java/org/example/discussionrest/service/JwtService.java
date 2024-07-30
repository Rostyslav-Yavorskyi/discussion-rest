package org.example.discussionrest.service;

import org.example.discussionrest.entity.User;

public interface JwtService {
    String extractEmail(String token);
    String generateToken(User userDetails);
    boolean isTokenValid(String token, User userDetails);

}
