package org.example.discussionrest.service.implementation;

import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userDao.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
}
