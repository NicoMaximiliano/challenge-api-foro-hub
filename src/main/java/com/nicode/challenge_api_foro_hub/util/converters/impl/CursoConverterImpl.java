package com.nicode.challenge_api_foro_hub.util.converters.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.curso.CursoRequestDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.CursoEntity;
import com.nicode.challenge_api_foro_hub.util.converters.CursoConverter;
import org.springframework.stereotype.Component;

@Component
public class CursoConverterImpl implements CursoConverter {

    @Override
    public CursoEntity toCursoEntity(CursoRequestDto curso) {
        return new CursoEntity(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }

}
