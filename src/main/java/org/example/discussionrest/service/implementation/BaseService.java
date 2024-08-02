package org.example.discussionrest.service.implementation;

import org.example.discussionrest.dao.AuditoriumDao;
import org.example.discussionrest.dao.DiscussionDao;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.entity.Discussion;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.*;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseService {

    @Autowired
    protected DiscussionDao discussionDao;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected AuditoriumDao auditoriumDao;

    protected Discussion findDiscussionByIdOrElseThrowException(int id) throws DiscussionNotFoundException {
        return discussionDao.findOne(id).orElseThrow(() -> createDiscussionNotFoundException(id));
    }

    protected Discussion findDiscussionByIdWithAuditoriumOrElseThrowException(int id) throws DiscussionNotFoundException {
        return discussionDao.findOneWithAuditorium(id).orElseThrow(() -> createDiscussionNotFoundException(id));
    }

    protected Discussion findDiscussionByIdWithUsersOrElseThrowException(int id) throws DiscussionNotFoundException {
        return discussionDao.findOneWithUsers(id).orElseThrow(() -> createDiscussionNotFoundException(id));
    }

    protected User findUserByIdOrElseThrowException(int id) throws UserNotFoundException {
        return userDao.findOne(id).orElseThrow(() -> createUserNotFoundException(id));
    }

    protected User findUserByIdWithDiscussionsOrElseThrowException(int id) throws UserNotFoundException {
        return userDao.findOneWithDiscussions(id).orElseThrow(() -> createUserNotFoundException(id));
    }

    protected User findUserByIdWithDiscussionsAndAuditoriumOrElseThrowException(int id) throws UserNotFoundException {
        return userDao.findOneWithDiscussionsAndAuditorium(id).orElseThrow(() -> createUserNotFoundException(id));
    }

    protected Auditorium findAuditoriumByIdOrElseThrowException(int id) throws AuditoriumNotFoundException {
        return auditoriumDao.findOne(id).orElseThrow(() -> createAuditoriumNotFoundException(id));
    }

    protected AuditoriumNotFoundException createAuditoriumNotFoundException(int id) {
        return new AuditoriumNotFoundException(String.format("Auditorium not found with id %d", id));
    }

    protected UserAlreadyRegisteredException createUserAlreadyRegisteredException(String email) {
        return new UserAlreadyRegisteredException(String.format("User with email %s already registered", email));
    }

    protected UserAlreadyJoinedToDiscussionException createUserAlreadyJoinedToDiscussionException() {
        return new UserAlreadyJoinedToDiscussionException("Already joined");
    }

    protected DiscussionNotFoundException createDiscussionNotFoundExceptionWithUserId(int id, int userId) {
        return new DiscussionNotFoundException(String.format("Discussion not found with id %d and userId %d", id, userId));
    }

    protected DiscussionNotFoundException createDiscussionNotFoundException(int id) {
        return new DiscussionNotFoundException(String.format("Discussion not found with id %d", id));
    }

    protected UserNotFoundException createUserNotFoundException(int id) {
        return new UserNotFoundException(String.format("User not found with id %d", id));
    }
}
