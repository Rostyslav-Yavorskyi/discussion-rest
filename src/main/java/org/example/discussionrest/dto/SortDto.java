package org.example.discussionrest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SortDto {

    List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public enum Direction {
        ASC, DESC
    }


    @Data
    public static final class Order {
        private final String field;
        private final Direction direction;
    }
}
