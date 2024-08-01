package org.example.discussionrest.dao.implementation;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.DiscussionDao;
import org.example.discussionrest.entity.Discussion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DiscussionDaoImpl implements DiscussionDao {

    private final EntityManager entityManager;

    @Override
    public void insert(Discussion discussion) {
        System.out.println(discussion);
        entityManager.persist(discussion);
    }

    @Override
    public List<Discussion> findAll() {
        return entityManager.createQuery("from Discussion order by id", Discussion.class).getResultList();
    }

    @Override
    public Optional<Discussion> findOne(int id) {
        return Optional.ofNullable(entityManager.find(Discussion.class, id));
    }

    @Override
    public boolean delete(int id) {
        int rows = entityManager.createQuery("delete from Discussion where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return rows != 0;
    }
}
