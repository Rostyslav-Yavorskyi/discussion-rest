package org.example.discussionrest.dto;

import lombok.Data;
import org.example.discussionrest.entity.User;

@Data
public class UserInternalDto {
    private int id;
    private String email;
    private String password;
    private User.Role role;
}
