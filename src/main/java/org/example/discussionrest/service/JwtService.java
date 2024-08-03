package org.example.discussionrest.service;

import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.entity.User;

public interface JwtService {

    int extractId(String token);

    TokenReadDto generateToken(User user);

    boolean isTokenInvalid(String token);

}
