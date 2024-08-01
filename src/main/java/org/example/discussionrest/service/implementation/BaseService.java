package org.example.discussionrest.service.implementation;

import org.example.discussionrest.dao.DiscussionDao;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.Discussion;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseService {

    @Autowired
    protected DiscussionDao discussionDao;
    @Autowired
    protected UserDao userDao;

    protected Discussion findDiscussionByIdOrElseThrowException(int id) throws DiscussionNotFoundException {
        return discussionDao.findOne(id).orElseThrow(() -> new DiscussionNotFoundException(id));
    }

    protected User findUserByIdOrElseThrowException(int id) throws UserNotFoundException {
        return userDao.findOne(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
