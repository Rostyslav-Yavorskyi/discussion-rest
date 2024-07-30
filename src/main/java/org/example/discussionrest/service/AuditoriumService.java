package org.example.discussionrest.service;

import org.example.discussionrest.dto.AuditoriumCreateDto;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.dto.AuditoriumUpdateDto;
import org.example.discussionrest.exception.AuditoriumNotFoundException;

import java.util.List;

public interface AuditoriumService {

    AuditoriumReadDto insertIfNotExists(AuditoriumCreateDto auditoriumCreateDto);

    List<AuditoriumReadDto> findAll();

    AuditoriumReadDto findOne(int id) throws AuditoriumNotFoundException;

    AuditoriumReadDto update(int id, AuditoriumUpdateDto auditoriumUpdateDto) throws AuditoriumNotFoundException;

    void delete(int id) throws AuditoriumNotFoundException;
}
