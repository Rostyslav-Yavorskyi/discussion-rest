package org.example.discussionrest.exception;

import org.example.discussionrest.entity.Discussion;

public class DiscussionNotFoundException extends RecordNotFoundException {

    public DiscussionNotFoundException(long id) {
        super(Discussion.class, id);
    }

    public DiscussionNotFoundException(long id, int userId) {
        super(String.format("Discussion not found with id %d and userId %d", id, userId));
    }
}
