package org.example.discussionrest.dao;

import org.example.discussionrest.entity.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionDao {

    void insert(Discussion discussion);

    List<Discussion> findAllWithAuditorium();

    Optional<Discussion> findOne(int id);

    Optional<Discussion> findOneWithUsers(int id);

    Optional<Discussion> findOneWithAuditorium(int id);

    boolean delete(int id);
}
