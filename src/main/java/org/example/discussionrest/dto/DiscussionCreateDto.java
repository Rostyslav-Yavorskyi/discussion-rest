package org.example.discussionrest.dto;

import lombok.Data;

@Data
public class DiscussionCreateDto {
    private String topic;
    private int auditoriumId;
}
