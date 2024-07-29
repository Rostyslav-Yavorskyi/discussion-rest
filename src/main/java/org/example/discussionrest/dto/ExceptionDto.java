package org.example.discussionrest.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionDto {
    private int status;
    private String message;
    private long timestamp;
}
