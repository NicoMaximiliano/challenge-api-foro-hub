package com.nicode.challenge_api_foro_hub.persistence.repositories;


import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioDtoAuthRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.usuario.UsuarioDto;

public interface UsuarioRepository {

    UsuarioDto getUsuarioByNombre(String nombre);

    void saveUsuario(UsuarioDtoAuthRequest authRequest);

}
