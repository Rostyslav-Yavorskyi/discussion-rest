package org.example.discussionrest.dao.implementation;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Override
    public void insert(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User order by id", User.class).getResultList();
    }

    @Override
    public Optional<User> findOne(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public boolean delete(int id) {
        int rows = entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return rows != 0;
    }
}
