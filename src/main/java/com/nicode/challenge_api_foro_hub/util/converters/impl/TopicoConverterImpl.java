package com.nicode.challenge_api_foro_hub.util.converters.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.TopicoEntity;
import com.nicode.challenge_api_foro_hub.util.converters.CursoConverter;
import com.nicode.challenge_api_foro_hub.util.converters.TopicoConverter;
import com.nicode.challenge_api_foro_hub.util.converters.UsuarioConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TopicoConverterImpl implements TopicoConverter {

    private final CursoConverter cursoConverter;
    private final UsuarioConverter usuarioConverter;

    @Override
    public TopicoResponseDto toTopicoDto(TopicoEntity topicoEntity) {
        return new TopicoResponseDto(
                topicoEntity.getTitulo(),
                topicoEntity.getMensaje(),
                topicoEntity.getFechaCreacion().toString(),
                topicoEntity.getEstado(),
                topicoEntity.getUsuario().getNombre(),
                topicoEntity.getCurso().getNombre()
        );
    }

    @Override
    public List<TopicoResponseDto> toTopicoDtoList(List<TopicoEntity> topicoEntities) {
        return topicoEntities.stream()
                .map(this::toTopicoDto)
                .toList();
    }

    @Override
    public TopicoEntity toTopicoEntity(TopicoRequestDto topico) {
        return new TopicoEntity(
                topico.getTitulo(),
                topico.getMensaje(),
                LocalDateTime.now(),
                true,
                usuarioConverter.toUsuarioEntity(topico.getUsuario()),
                cursoConverter.toCursoEntity(topico.getCurso())
        );
    }

}
