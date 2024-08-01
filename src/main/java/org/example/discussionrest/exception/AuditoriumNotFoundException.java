package org.example.discussionrest.exception;

public class AuditoriumNotFoundException extends RecordNotFoundException{

    public AuditoriumNotFoundException(String message) {
        super(message);
    }
}
