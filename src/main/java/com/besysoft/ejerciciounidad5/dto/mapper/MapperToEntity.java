package com.besysoft.ejerciciounidad5.dto.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface MapperToEntity <DTO, Entity> extends Function<DTO, Entity> {

    default List<Entity> toEntity(List<DTO> dtoList) {
        return dtoList.stream()
                .map(this::apply)
                .collect(Collectors.toUnmodifiableList());
    }

    default Entity toEntity(DTO dto) {
        return apply(dto);
    }

}
