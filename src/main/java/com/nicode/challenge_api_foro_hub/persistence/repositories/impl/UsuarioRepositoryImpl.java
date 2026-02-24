package com.nicode.challenge_api_foro_hub.persistence.repositories.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
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
    public boolean existsByNombre(String nombre) {
        return usuarioJpaRepository.existsByNombre(nombre);
    }

    @Override
    public void saveUsuario(UsuarioAuthRequestDto authRequest) {
        UsuarioEntity usuarioEntity = usuarioConverter.toUsuarioEntity(authRequest);
        usuarioJpaRepository.save(usuarioEntity);
    }


}
