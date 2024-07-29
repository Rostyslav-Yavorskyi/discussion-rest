package org.example.discussionrest.service;

import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.AuditoriumDao;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.exception.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService{

    private final AuditoriumDao auditoriumDao;

    @Override
    public List<Auditorium> findAll() {
        return auditoriumDao.findAll();
    }

    @Override
    public Auditorium findOne(int id) throws RecordNotFoundException {
        return auditoriumDao.findOne(id).orElseThrow(() -> new AuditoriumNotFoundException(id));
    }
}
