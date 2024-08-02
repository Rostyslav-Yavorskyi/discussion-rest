package org.example.discussionrest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"users", "auditorium"})
@EqualsAndHashCode(exclude = {"users", "auditorium"})
@Entity
@Table(name = "discussion")
@NamedEntityGraph(
        name = "withAuditorium",
        attributeNodes = @NamedAttributeNode("auditorium")
)
@NamedEntityGraph(
        name = "withUsers",
        attributeNodes = @NamedAttributeNode("users")
)
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;

    @ManyToMany(mappedBy = "discussions")
    private Set<User> users = new HashSet<>();
}
