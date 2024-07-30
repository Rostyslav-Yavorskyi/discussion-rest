package org.example.discussionrest.exception;

public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException(String email) {
        super(String.format("User with email %s already registered", email));
    }
}
