package com.besysoft.ejerciciounidad5.dto.mapper;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.dto.PeliculaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    Pelicula toEntity(PeliculaDTO dto);
    List<Pelicula> toEntityList(List<PeliculaDTO> dtoList);

    @InheritInverseConfiguration
    @Mapping(target = "personajes", ignore = true)
    PeliculaDTO toDTO(Pelicula entity);
    List<PeliculaDTO> toDTOList(List<Pelicula> entityList);

}
