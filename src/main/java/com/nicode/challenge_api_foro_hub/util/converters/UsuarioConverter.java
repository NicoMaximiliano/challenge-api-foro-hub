package com.nicode.challenge_api_foro_hub.util.converters;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioDtoAuthRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioDtoRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.usuario.UsuarioDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.UsuarioEntity;

public interface UsuarioConverter {

    UsuarioDto toUsuarioDto(UsuarioEntity usuario);
    UsuarioEntity toUsuarioEntity(UsuarioDtoRequest usuario);
    UsuarioEntity toUsuarioEntity(UsuarioDtoAuthRequest usuario);
}
