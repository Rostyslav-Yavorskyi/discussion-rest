package org.example.discussionrest.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dao.AuditoriumDao;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.exception.RecordNotFoundException;
import org.example.discussionrest.mapper.AuditoriumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService {

    private final AuditoriumDao auditoriumDao;
    private final AuditoriumMapper auditoriumMapper;

    @Override
    public List<AuditoriumReadDto> findAll() {
        return auditoriumMapper.auditoriumsToAuditoriumReadDtos(auditoriumDao.findAll());
    }

    @Override
    public AuditoriumReadDto findOne(int id) throws RecordNotFoundException {
        Auditorium auditorium = auditoriumDao.findOne(id).orElseThrow(() -> new AuditoriumNotFoundException(id));
        return auditoriumMapper.auditoriumToAuditoriumReadDto(auditorium);
    }
}
