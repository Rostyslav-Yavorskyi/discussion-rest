package org.example.discussionrest.service;

import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;

public interface UserService {

    void register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException;
    User findByEmail(String email) throws UserNotFoundException;
}
