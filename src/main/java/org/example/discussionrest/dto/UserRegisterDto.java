package org.example.discussionrest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterDto {

    @Email
    private String email;

    @Length(min = 4)
    private String password;

    @Length(min = 3)
    private String firstName;

    @Length(min = 3)
    private String lastName;
}
