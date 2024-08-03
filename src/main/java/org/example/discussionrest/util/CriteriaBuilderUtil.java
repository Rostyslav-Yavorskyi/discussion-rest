package org.example.discussionrest.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.example.discussionrest.dto.SortDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriteriaBuilderUtil {

    public List<Order> buildOrders(CriteriaBuilder criteriaBuilder, Root<?> root, SortDto sortDto) {
        return sortDto.getOrders().stream()
                .map(order -> createOrder(criteriaBuilder, root, order))
                .toList();
    }

    private Order createOrder(CriteriaBuilder criteriaBuilder, Root<?> root, SortDto.Order order) {
        if (order.getDirection() == SortDto.Direction.ASC) {
            return criteriaBuilder.asc(root.get(order.getField()));
        } else {
            return criteriaBuilder.desc(root.get(order.getField()));
        }
    }
}
