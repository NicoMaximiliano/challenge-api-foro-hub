package com.nicode.challenge_api_foro_hub.util.converters.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoDtoResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;
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
    public TopicoDtoResponse toTopicoDto(TopicoEntity topicoEntity) {
        return new TopicoDtoResponse(
                topicoEntity.getTitulo(),
                topicoEntity.getMensaje(),
                topicoEntity.getFechaCreacion().toString(),
                topicoEntity.getEstado(),
                topicoEntity.getUsuario().getNombre(),
                topicoEntity.getCurso().getNombre()
        );
    }

    @Override
    public List<TopicoDtoResponse> toTopicoDtoList(List<TopicoEntity> topicoEntities) {
        return topicoEntities.stream()
                .map(this::toTopicoDto)
                .toList();
    }

    @Override
    public TopicoEntity toTopicoEntity(TopicoDtoRequest topico) {
        //TopicoEntity topicoEntity = new TopicoEntity();
        //LocalDateTime fechaCreacion = LocalDateTime.now();

//        topicoEntity.setTitulo(topico.getTitulo());
//        topicoEntity.setMensaje(topico.getMensaje());
//        topicoEntity.setEstado(true);
//        topicoEntity.setFechaCreacion(fechaCreacion);
//        topicoEntity.setUsuario(usuarioConverter.toUsuarioEntity(topico.getUsuario()));
//        topicoEntity.setCurso(cursoConverter.toCursoEntity(topico.getCurso()));

        return new TopicoEntity(
                topico.getTitulo(),
                topico.getMensaje(),
                LocalDateTime.now(),
                true,
                usuarioConverter.toUsuarioEntity(topico.getUsuario()),
                cursoConverter.toCursoEntity(topico.getCurso())
        );

        //return topicoEntity;
    }
}
