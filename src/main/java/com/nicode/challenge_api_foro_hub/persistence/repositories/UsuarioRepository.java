package com.nicode.challenge_api_foro_hub.persistence.repositories;


import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.usuario.UsuarioDto;

public interface UsuarioRepository {

    UsuarioDto getUsuarioByNombre(String nombre);
    boolean existsByNombre(String nombre);
    void saveUsuario(UsuarioAuthRequestDto authRequest);

}
