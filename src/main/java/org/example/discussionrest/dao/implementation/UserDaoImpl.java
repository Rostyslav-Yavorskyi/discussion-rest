package org.example.discussionrest.dao.implementation;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.User;
import org.springframework.stereotype.Repository;

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
    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }
}
