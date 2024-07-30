package org.example.discussionrest.dao;

import org.example.discussionrest.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByEmail(String email);
}
