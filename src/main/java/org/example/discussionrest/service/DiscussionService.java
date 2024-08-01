package org.example.discussionrest.service;

import org.example.discussionrest.dto.DiscussionCreateDto;
import org.example.discussionrest.dto.DiscussionReadDto;
import org.example.discussionrest.dto.DiscussionUpdateDto;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.exception.DiscussionNotFoundException;

import java.util.List;

public interface DiscussionService {

    DiscussionReadDto insert(DiscussionCreateDto discussionCreateDto) throws AuditoriumNotFoundException;

    List<DiscussionReadDto> findAll();

    DiscussionReadDto findOne(int id) throws DiscussionNotFoundException;

    void update(int id, DiscussionUpdateDto discussionUpdateDto) throws DiscussionNotFoundException, AuditoriumNotFoundException;

    void delete(int id) throws DiscussionNotFoundException;
}
