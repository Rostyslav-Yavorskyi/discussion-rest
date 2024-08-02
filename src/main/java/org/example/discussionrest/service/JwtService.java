package org.example.discussionrest.service;

import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserInternalDto;
import org.example.discussionrest.dto.UserLoginDto;
import org.example.discussionrest.dto.UserRegisterDto;

public interface JwtService {

    String extractEmail(String token);

    TokenReadDto generateToken(UserLoginDto userLoginDto);

    TokenReadDto generateToken(UserRegisterDto userRegisterDto);

    boolean isTokenInvalid(String token, UserInternalDto User);

}
