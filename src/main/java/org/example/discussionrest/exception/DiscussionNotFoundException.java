package org.example.discussionrest.exception;

public class DiscussionNotFoundException extends RecordNotFoundException {

    public DiscussionNotFoundException(long id) {
        super(DiscussionNotFoundException.class, id);
    }
}
