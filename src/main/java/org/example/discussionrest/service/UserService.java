package org.example.discussionrest.service;

import org.example.discussionrest.dto.UserInternalDto;
import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserAlreadyJoinedToDiscussionException;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    void register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException;

    List<UserReadDto> findAll();

    List<UserReadDto> findAllByDiscussionId(int discussionId) throws DiscussionNotFoundException;

    UserReadDto findOne(int id) throws UserNotFoundException;

    UserInternalDto findByEmail(String email) throws UsernameNotFoundException;

    void update(int id, UserUpdateDto userUpdateDto) throws UserNotFoundException;

    void joinToDiscussion(int discussionId) throws DiscussionNotFoundException, UserAlreadyJoinedToDiscussionException, UserNotFoundException;

    void leaveFromDiscussion(int discussionId) throws DiscussionNotFoundException, UserNotFoundException;

    void delete(int id) throws UserNotFoundException;
}
