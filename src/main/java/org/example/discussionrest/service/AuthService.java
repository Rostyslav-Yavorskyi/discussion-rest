package org.example.discussionrest.service;

import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserLoginDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.exception.TokenExpiredException;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;

public interface AuthService {
    TokenReadDto register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException;
    TokenReadDto login(UserLoginDto userLoginDto) throws UserNotFoundException;
    void authenticate(String token) throws TokenExpiredException, UserNotFoundException;
}
