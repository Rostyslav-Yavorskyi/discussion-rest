package org.example.discussionrest.dto;

import lombok.Data;

@Data
public class DiscussionReadDto {
    private int id;
    private String topic;
    private AuditoriumReadDto auditorium;
}
