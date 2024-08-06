package org.example.discussionrest.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginDto {

    @Email
    private String email;

    @Length(min = 4)
    private String password;
}
