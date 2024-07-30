package org.example.discussionrest.dao;

import org.example.discussionrest.entity.User;

import java.util.Optional;

public interface UserDao {

    void insert(User user);
    Optional<User> findByEmail(String email);
}
