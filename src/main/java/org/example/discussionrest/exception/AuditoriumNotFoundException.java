package org.example.discussionrest.exception;

import org.example.discussionrest.entity.Auditorium;

public class AuditoriumNotFoundException extends RecordNotFoundException{

    public AuditoriumNotFoundException(long id) {
        super(Auditorium.class, id);
    }
}
