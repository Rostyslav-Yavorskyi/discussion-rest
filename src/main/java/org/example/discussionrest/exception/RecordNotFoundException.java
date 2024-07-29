package org.example.discussionrest.exception;

public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String entityName, long id) {
//        super("Record with id " + id + " not found for entity " + entityName);
        super(String.format("Record with id %d not found for entity %s", id, entityName));
    }
}
