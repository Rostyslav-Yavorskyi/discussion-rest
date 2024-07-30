package org.example.discussionrest.service;

import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email) throws UserNotFoundException;
}
