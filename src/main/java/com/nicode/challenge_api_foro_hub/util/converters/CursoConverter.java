package com.nicode.challenge_api_foro_hub.util.converters;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.curso.CursoDtoRequest;
import com.nicode.challenge_api_foro_hub.persistence.entities.CursoEntity;

public interface CursoConverter {

    CursoEntity toCursoEntity(CursoDtoRequest curso);
}
