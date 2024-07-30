package org.example.discussionrest.dto;

import lombok.Data;

@Data
public class UserSignInDto {
    private String email;
    private String password;
}
