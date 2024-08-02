package org.example.discussionrest.mapper;

import org.example.discussionrest.dto.DiscussionCreateDto;
import org.example.discussionrest.dto.DiscussionReadDto;
import org.example.discussionrest.dto.DiscussionUpdateDto;
import org.example.discussionrest.entity.Discussion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuditoriumMapper.class})
public interface DiscussionMapper {

    Discussion toEntity(DiscussionCreateDto discussionCreateDto);

    DiscussionReadDto toReadDto(Discussion discussion);

    List<DiscussionReadDto> toReadDto(List<Discussion> discussion);

    void update(@MappingTarget Discussion discussion, DiscussionUpdateDto discussionUpdateDto);
}
