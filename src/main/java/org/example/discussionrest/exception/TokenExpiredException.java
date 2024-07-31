package org.example.discussionrest.exception;

public class TokenExpiredException extends Exception {

    public TokenExpiredException() {
        super("Token is expired");
    }
}
