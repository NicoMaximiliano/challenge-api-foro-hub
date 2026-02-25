package com.nicode.challenge_api_foro_hub.util.converters.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.UsuarioDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.UsuarioEntity;
import com.nicode.challenge_api_foro_hub.util.PasswordUtil;
import com.nicode.challenge_api_foro_hub.util.converters.UsuarioConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsuarioConverterImpl implements UsuarioConverter {

    private final PasswordUtil passwordUtil;

    @Override
    public UsuarioDto toUsuarioDto(UsuarioEntity usuario) {
        return new UsuarioDto(
                usuario.getNombre(),
                usuario.getContrasenia()
        );
    }

    @Override
    public UsuarioEntity toUsuarioEntity(UsuarioRequestDto usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }

    @Override
    public UsuarioEntity toUsuarioEntity(UsuarioAuthRequestDto usuario) {
        return new UsuarioEntity(
                usuario.getNombre(),
                usuario.getEmail(),
                passwordUtil.encryptPassword(usuario.getContrasenia())
        );
    }

}
