package org.example.discussionrest.service;

import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserLoginDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.entity.User;

public interface JwtService {
    String extractEmail(String token);
    TokenReadDto generateToken(UserLoginDto userLoginDto);
    TokenReadDto generateToken(UserRegisterDto userRegisterDto);
    boolean isTokenValid(String token, User User);

}
