package org.example.discussionrest.service;

import org.example.discussionrest.dto.UserSignInDto;
import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.exception.UserNotFoundException;

public interface AuthenticationService {
    TokenReadDto signIn(UserSignInDto userSignInDto) throws UserNotFoundException;
}
