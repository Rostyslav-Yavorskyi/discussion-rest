package org.example.discussionrest.service;

import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.exception.RecordNotFoundException;

import java.util.List;

public interface AuditoriumService {

    List<Auditorium> findAll();

    Auditorium findOne(int id) throws RecordNotFoundException;
}
