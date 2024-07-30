package org.example.discussionrest.service;

import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserNotFoundException;

public interface UserService {

    User findByEmail(String email) throws UserNotFoundException;
}
