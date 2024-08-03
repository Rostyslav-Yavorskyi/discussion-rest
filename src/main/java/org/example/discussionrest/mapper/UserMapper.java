package org.example.discussionrest.mapper;

import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegisterDto userRegisterDto);

    UserReadDto toReadDto(User user);

    List<UserReadDto> toReadDto(List<User> users);

    List<UserReadDto> toReadDto(Set<User> users);

    void update(@MappingTarget User user, UserUpdateDto userUpdateDto);
}
