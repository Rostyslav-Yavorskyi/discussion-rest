package org.example.discussionrest.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RecordNotFoundException extends UsernameNotFoundException {

    public RecordNotFoundException(Class<?> entityClass, long id) {
//        super("Record with id " + id + " not found for entity " + entityName);
        super(String.format("Record with id %d not found for entity %s", id, entityClass.getSimpleName()));
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
