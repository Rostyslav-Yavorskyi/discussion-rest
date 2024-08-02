package org.example.discussionrest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"password", "discussions"})
@Entity
@Table(name = "user")
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
    private List<Discussion> discussions = new ArrayList<>();

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
