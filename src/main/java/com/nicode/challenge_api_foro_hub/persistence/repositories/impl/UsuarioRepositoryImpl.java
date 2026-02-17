package com.nicode.challenge_api_foro_hub.persistence.repositories.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioDtoAuthRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.usuario.UsuarioDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.UsuarioEntity;
import com.nicode.challenge_api_foro_hub.persistence.repositories.UsuarioRepository;
import com.nicode.challenge_api_foro_hub.persistence.repositories.jpa.UsuarioJpaRepository;
import com.nicode.challenge_api_foro_hub.util.converters.UsuarioConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;

    private final UsuarioConverter usuarioConverter;


    @Override
    public UsuarioDto getUsuarioByNombre(String nombre) {
        UsuarioEntity usuarioEntity = usuarioJpaRepository.findByNombre(nombre);
        return usuarioConverter.toUsuarioDto(usuarioEntity);
    }

    @Override
    public void saveUsuario(UsuarioDtoAuthRequest authRequest) {
        UsuarioEntity usuarioEntity = usuarioConverter.toUsuarioEntity(authRequest);
        usuarioJpaRepository.save(usuarioEntity);
    }


}
