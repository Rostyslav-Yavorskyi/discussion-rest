package org.example.discussionrest.service;

import org.example.discussionrest.entity.MyUserDetails;

public interface JwtService {
    String extractUserEmail(String token);
    String generateToken(MyUserDetails userDetails);
    boolean isTokenValid(String token, MyUserDetails userDetails);

}
