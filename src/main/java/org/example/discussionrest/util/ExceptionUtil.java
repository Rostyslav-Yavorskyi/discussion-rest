package org.example.discussionrest.util;

import org.example.discussionrest.exception.*;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {

    public UserAlreadyRegisteredException createUserAlreadyRegisteredException(String email) {
        return new UserAlreadyRegisteredException(String.format("User with email %s already registered", email));
    }

    public UserAlreadyJoinedToDiscussionException createUserAlreadyJoinedToDiscussionException() {
        return new UserAlreadyJoinedToDiscussionException("Already joined");
    }

    public UserNotFoundException createUserNotFoundException(int id) {
        return new UserNotFoundException(String.format("User not found with id %d", id));
    }

    public UserNotFoundException createUserNotFoundException(String email) {
        return new UserNotFoundException(String.format("User not found with email %s", email));
    }

    public DiscussionNotFoundException createDiscussionNotFoundExceptionWithUserId(int id, int userId) {
        return new DiscussionNotFoundException(String.format("Discussion not found with id %d and userId %d", id, userId));
    }

    public DiscussionNotFoundException createDiscussionNotFoundException(int id) {
        return new DiscussionNotFoundException(String.format("Discussion not found with id %d", id));
    }

    public AuditoriumNotFoundException createAuditoriumNotFoundException(int id) {
        return new AuditoriumNotFoundException(String.format("Auditorium not found with id %d", id));
    }
}
