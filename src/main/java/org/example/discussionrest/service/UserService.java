package org.example.discussionrest.service;

import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    void register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException;
    List<UserReadDto> findAll();
    UserReadDto findOne(int id) throws UserNotFoundException;
    User findByEmail(String email) throws UsernameNotFoundException;
    void update(int id, UserUpdateDto userUpdateDto) throws UserNotFoundException;
    void delete(int id) throws UserNotFoundException;
}
