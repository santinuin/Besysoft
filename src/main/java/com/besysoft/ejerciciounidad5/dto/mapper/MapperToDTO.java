package com.besysoft.ejerciciounidad5.dto.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface MapperToDTO<Entity, DTO> extends Function<Entity, DTO> {

    default List<DTO> toDTO(List<Entity> entityList) {
        return entityList.stream()
                .map(this::apply)
                .collect(Collectors.toUnmodifiableList());
    }

    default DTO toDTO(Entity entity) {
        return apply(entity);
    }


}
