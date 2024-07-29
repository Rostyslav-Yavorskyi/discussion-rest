package org.example.discussionrest.mapper;

import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.entity.Auditorium;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {
    AuditoriumReadDto auditoriumToAuditoriumReadDto(Auditorium auditorium);
    List<AuditoriumReadDto> auditoriumsToAuditoriumReadDtos(List<Auditorium> auditoriums);
}
