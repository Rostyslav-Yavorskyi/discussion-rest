package org.example.discussionrest.exception;

public class UserNotFoundException extends RecordNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
