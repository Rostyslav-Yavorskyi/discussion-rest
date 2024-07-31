package org.example.discussionrest.dto;

import lombok.Data;
import org.example.discussionrest.entity.User;

@Data
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private User.Role role;
}
