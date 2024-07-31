package org.example.discussionrest.exception;

import org.example.discussionrest.entity.User;

public class UserNotFoundException extends RecordNotFoundException {

    public UserNotFoundException(long id) {
        super(User.class, id);
    }
}
