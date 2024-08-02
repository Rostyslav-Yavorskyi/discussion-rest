package org.example.discussionrest.service.implementation;

import org.example.discussionrest.dao.AuditoriumDao;
import org.example.discussionrest.dao.DiscussionDao;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.entity.Discussion;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.util.ExceptionUtil;
import org.example.discussionrest.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseService {

    @Autowired
    protected DiscussionDao discussionDao;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected AuditoriumDao auditoriumDao;
    @Autowired
    protected ExceptionUtil exceptionUtil;

    protected Discussion findDiscussionByIdOrElseThrowException(int id, FetchType fetchType) throws DiscussionNotFoundException {
        return (switch (fetchType) {
            case WITH_AUDITORIUM -> discussionDao.findOneWithAuditorium(id);
            case WITH_USERS -> discussionDao.findOneWithUsers(id);
            default -> discussionDao.findOne(id);
        }).orElseThrow(() -> exceptionUtil.createDiscussionNotFoundException(id));
    }

    protected User findUserByIdOrElseThrowException(int id, FetchType fetchType) throws UserNotFoundException {
        return (switch (fetchType) {
            case WITH_DISCUSSIONS -> userDao.findOneWithDiscussions(id);
            case WITH_DISCUSSIONS_AND_AUDITORIUM -> userDao.findOneWithDiscussionsAndAuditorium(id);
            default -> userDao.findOne(id);
        }).orElseThrow(() -> exceptionUtil.createUserNotFoundException(id));
    }

    protected Auditorium findAuditoriumByIdOrElseThrowException(int id) throws AuditoriumNotFoundException {
        return auditoriumDao.findOne(id).orElseThrow(() -> exceptionUtil.createAuditoriumNotFoundException(id));
    }

    protected enum FetchType {
        DEFAULT,
        WITH_AUDITORIUM,
        WITH_DISCUSSIONS,
        WITH_DISCUSSIONS_AND_AUDITORIUM,
        WITH_USERS,
    }
}
