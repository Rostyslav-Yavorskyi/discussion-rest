package org.example.discussionrest.mapper;

import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegisterDto userRegisterDto);
}
