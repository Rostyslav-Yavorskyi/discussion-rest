package org.example.discussionrest.mapper;

import org.example.discussionrest.dto.AuditoriumCreateDto;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.dto.AuditoriumUpdateDto;
import org.example.discussionrest.entity.Auditorium;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {

    AuditoriumReadDto toReadDto(Auditorium auditorium);

    List<AuditoriumReadDto> toReadDto(List<Auditorium> auditoriums);

    Auditorium toEntity(AuditoriumCreateDto auditoriumCreateDto);

    void update(@MappingTarget Auditorium auditorium, AuditoriumUpdateDto auditoriumUpdateDto);
}
