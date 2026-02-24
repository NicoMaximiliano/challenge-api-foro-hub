package com.nicode.challenge_api_foro_hub.util.converters;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.usuario.UsuarioDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.UsuarioEntity;

public interface UsuarioConverter {

    UsuarioDto toUsuarioDto(UsuarioEntity usuario);
    UsuarioEntity toUsuarioEntity(UsuarioRequestDto usuario);
    UsuarioEntity toUsuarioEntity(UsuarioAuthRequestDto usuario);
}
