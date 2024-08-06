package org.example.discussionrest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AuditoriumUpdateDto {

    @Min(1)
    @Max(1000)
    private int number;
}
