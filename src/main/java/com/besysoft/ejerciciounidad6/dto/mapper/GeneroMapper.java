package com.besysoft.ejerciciounidad6.dto.mapper;

import com.besysoft.ejerciciounidad6.domain.entity.Genero;
import com.besysoft.ejerciciounidad6.dto.GeneroDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    Genero toEntity(GeneroDTO dto);
    List<Genero> toEntityList(List<GeneroDTO> dtoList);

    @InheritInverseConfiguration
    @Mapping(target = "peliculas", ignore = true)
    GeneroDTO toDTO(Genero entity);
    List<GeneroDTO> toDTOList(List<Genero> entityList);
}
