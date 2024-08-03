package org.example.discussionrest.util;

import org.example.discussionrest.dto.SortDto;
import org.springframework.stereotype.Component;

@Component
public class SortDtoBuilder {

    private final static SortDto.Direction DEFAULT_DIRECTION = SortDto.Direction.ASC;

    public SortDto buildSortDto(String sortParams) {
        SortDto sortDto = new SortDto();

        for (String sortParam : sortParams.split(",")) {
            sortDto.addOrder(parseSortParam(sortParam.strip()));
        }

        return sortDto;
    }

    private SortDto.Order parseSortParam(String sortParam) {
        String field;
        SortDto.Direction direction;

        switch (sortParam.charAt(0)) {
            case '+' -> {
                direction = SortDto.Direction.ASC;
                field = sortParam.substring(1);
            }
            case '-' -> {
                direction = SortDto.Direction.DESC;
                field = sortParam.substring(1);
            }
            default -> {
                direction = DEFAULT_DIRECTION;
                field = sortParam;
            }
        }

        return new SortDto.Order(field, direction);
    }
}
