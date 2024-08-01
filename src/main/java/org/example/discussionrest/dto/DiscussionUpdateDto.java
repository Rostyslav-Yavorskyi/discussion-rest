package org.example.discussionrest.dto;

import lombok.Data;

@Data
public class DiscussionUpdateDto {
    private String topic;
    private int auditoriumId;
}
