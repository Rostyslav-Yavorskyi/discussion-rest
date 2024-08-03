package org.example.discussionrest.dao;

import org.example.discussionrest.dto.SortDto;
import org.example.discussionrest.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void insert(User user);

    List<User> findAll(SortDto sortDto);

    Optional<User> findOne(int id);

    Optional<User> findOneWithDiscussions(int id);

    Optional<User> findOneWithDiscussionsAndAuditorium(int id);

    Optional<User> findByEmail(String email);

    void update(User user);

    boolean delete(int id);
}
