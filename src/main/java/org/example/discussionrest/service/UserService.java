package org.example.discussionrest.service;

import org.example.discussionrest.dto.SortDto;
import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserAlreadyJoinedToDiscussionException;
import org.example.discussionrest.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    List<UserReadDto> findAll(SortDto sortDto);

    List<UserReadDto> findAllByDiscussionId(int discussionId) throws DiscussionNotFoundException;

    UserReadDto findOne(int id) throws UserNotFoundException;

    void update(int id, UserUpdateDto userUpdateDto) throws UserNotFoundException;

    void joinToDiscussion(int discussionId) throws DiscussionNotFoundException, UserAlreadyJoinedToDiscussionException, UserNotFoundException;

    void leaveFromDiscussion(int discussionId) throws DiscussionNotFoundException, UserNotFoundException;

    void delete(int id) throws UserNotFoundException;
}
