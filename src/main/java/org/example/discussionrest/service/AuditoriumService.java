package org.example.discussionrest.service;

import org.example.discussionrest.dto.AuditoriumCreateDto;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.exception.RecordNotFoundException;

import java.util.List;

public interface AuditoriumService {

    AuditoriumReadDto insertIfNotExists(AuditoriumCreateDto auditoriumCreateDto);

    List<AuditoriumReadDto> findAll();

    AuditoriumReadDto findOne(int id) throws RecordNotFoundException;
}
