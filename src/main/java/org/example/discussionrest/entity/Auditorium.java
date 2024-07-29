package org.example.discussionrest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private int number;
}


