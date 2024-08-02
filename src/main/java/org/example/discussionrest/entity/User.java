package org.example.discussionrest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"password", "discussions"})
@EqualsAndHashCode(exclude = "discussions")
@Entity
@Table(name = "user")
@NamedEntityGraph(
        name = "withDiscussions",
        attributeNodes = @NamedAttributeNode("discussions")
)
@NamedEntityGraph(
        name = "withDiscussionsAndAuditorium",
        attributeNodes = @NamedAttributeNode(value = "discussions", subgraph = "discussions"),
        subgraphs = @NamedSubgraph(name = "discussions", attributeNodes = @NamedAttributeNode("auditorium"))
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_discussion",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "discussion_id"))
    private Set<Discussion> discussions = new HashSet<>();

    public void addDiscussion(Discussion discussion) {
        discussions.add(discussion);
    }

    public boolean removeDiscussion(Discussion discussion) {
        return discussions.remove(discussion);
    }

    public boolean discussionExists(Discussion discussion) {
        return discussions.contains(discussion);
    }

    public enum Role {
        USER,
        ADMIN
    }
}
