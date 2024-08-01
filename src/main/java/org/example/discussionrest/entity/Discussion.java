package org.example.discussionrest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "discussion")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id", nullable = false)
    Auditorium auditorium;
}
