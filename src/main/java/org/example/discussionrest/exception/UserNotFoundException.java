package org.example.discussionrest.exception;

public class UserNotFoundException extends RecordNotFoundException {

    public UserNotFoundException(String email) {
        super(String.format("User with email '%s' not found", email));
    }
}
