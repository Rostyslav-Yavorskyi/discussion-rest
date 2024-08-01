package org.example.discussionrest.exception;

public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(Class<?> entityClass, long id) {
        super(String.format("Record with id %d not found for entity %s", id, entityClass.getSimpleName()));
    }
}
