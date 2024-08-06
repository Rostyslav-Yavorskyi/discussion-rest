package org.example.discussionrest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.discussionrest.entity.User;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserReadDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private User.Role role;
}
