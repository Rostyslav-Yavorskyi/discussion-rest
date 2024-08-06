package org.example.discussionrest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.discussionrest.entity.User;
import org.hibernate.validator.constraints.Length;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserUpdateDto {

    @Length(min = 3)
    private String firstName;

    @Length(min = 3)
    private String lastName;

    private User.Role role;
}
