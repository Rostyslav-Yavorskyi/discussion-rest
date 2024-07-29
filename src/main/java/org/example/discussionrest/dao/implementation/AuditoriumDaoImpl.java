package org.example.discussionrest.dao.implementation;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.AuditoriumDao;
import org.example.discussionrest.entity.Auditorium;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuditoriumDaoImpl implements AuditoriumDao {

    private final EntityManager entityManager;

    @Override
    public void insert(Auditorium auditorium) {
        entityManager.persist(auditorium);
    }

    @Override
    public List<Auditorium> findAll() {
        return entityManager.createQuery("from Auditorium order by id", Auditorium.class).getResultList();
    }

    @Override
    public Optional<Auditorium> findOne(int id) {
        return Optional.ofNullable(entityManager.find(Auditorium.class, id));
    }

    @Override
    public Optional<Auditorium> findByNumber(int number) {
        return entityManager.createQuery("from Auditorium where number = :number", Auditorium.class)
                .setParameter("number", number)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }

    @Override
    public boolean delete(int id) {
        int rows = entityManager.createQuery("delete from Auditorium where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return rows != 0;
    }
}
