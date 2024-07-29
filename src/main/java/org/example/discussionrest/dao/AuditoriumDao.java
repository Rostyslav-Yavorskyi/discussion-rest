package org.example.discussionrest.dao;

import org.example.discussionrest.entity.Auditorium;

import java.util.List;
import java.util.Optional;

public interface AuditoriumDao {

    void insert(Auditorium auditorium);

    List<Auditorium> findAll();

    Optional<Auditorium> findOne(int id);

    Optional<Auditorium> findByNumber(int number);

    boolean delete(int id);
}
