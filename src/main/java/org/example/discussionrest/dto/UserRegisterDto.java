package org.example.discussionrest.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
