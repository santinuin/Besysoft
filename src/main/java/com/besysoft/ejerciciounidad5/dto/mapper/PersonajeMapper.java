package com.besysoft.ejerciciounidad5.dto.mapper;

import com.besysoft.ejerciciounidad5.domain.entity.Personaje;
import com.besysoft.ejerciciounidad5.dto.PersonajeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonajeMapper {

    Personaje toEntity(PersonajeDTO dto);
    List<Personaje> toEntityList(List<PersonajeDTO> dtoList);

    @InheritInverseConfiguration
    @Mapping(target = "pelicula.personajes", ignore = true)
    PersonajeDTO toDTO(Personaje entity);
    List<PersonajeDTO> toDTOList(List<Personaje> entityList);
}
