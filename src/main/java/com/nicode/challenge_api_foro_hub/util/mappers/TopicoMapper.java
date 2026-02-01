package com.nicode.challenge_api_foro_hub.util.mappers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.TopicoRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.TopicoResponse;
import com.nicode.challenge_api_foro_hub.persistence.entities.TopicoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface TopicoMapper {
    @Mappings({
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "mensaje", target = "mensaje"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "usuario.nombre", target = "nombreAutor"),
            @Mapping(source = "curso.nombre", target = "cursoNombre"),
            @Mapping(target = "fechaCreacion", ignore = true)
    })
    TopicoResponse toTopicoResponse(TopicoEntity topicoEntity);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "mensaje", target = "mensaje"),
            @Mapping(source = "nombreAutor", target = "usuario"),
            @Mapping(source = "cursoNombre", target = "curso")
    })
    TopicoEntity toTopicoEntity(TopicoRequest topicoRequest);
}
