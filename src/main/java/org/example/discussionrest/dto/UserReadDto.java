package org.example.discussionrest.dto;

import lombok.Data;
import org.example.discussionrest.entity.User;

@Data
public class UserReadDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private User.Role role;
}
