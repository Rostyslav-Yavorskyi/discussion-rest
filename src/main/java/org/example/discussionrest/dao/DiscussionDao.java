package org.example.discussionrest.dao;

import org.example.discussionrest.entity.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionDao {

    void insert(Discussion discussion);

    List<Discussion> findAll();

    Optional<Discussion> findOne(int id);

    boolean delete(int id);
}
