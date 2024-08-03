package org.example.discussionrest.dao.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.dto.SortDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.util.CriteriaBuilderUtil;
import org.hibernate.graph.GraphSemantic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;
    private final CriteriaBuilderUtil criteriaBuilderUtil;

    @Override
    public void insert(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAll(SortDto sortDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        List<Order> orders = criteriaBuilderUtil.buildOrders(criteriaBuilder, root, sortDto);
        criteriaQuery.orderBy(orders);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<User> findOne(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findOneWithDiscussions(int id) {
        Map<String, Object> properties = Map.of(GraphSemantic.LOAD.getJakartaHintName(), entityManager.getEntityGraph("withDiscussions"));
        return Optional.ofNullable(entityManager.find(User.class, id, properties));
    }

    @Override
    public Optional<User> findOneWithDiscussionsAndAuditorium(int id) {
        Map<String, Object> properties = Map.of(GraphSemantic.LOAD.getJakartaHintName(), entityManager.getEntityGraph("withDiscussionsAndAuditorium"));
        return Optional.ofNullable(entityManager.find(User.class, id, properties));
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
