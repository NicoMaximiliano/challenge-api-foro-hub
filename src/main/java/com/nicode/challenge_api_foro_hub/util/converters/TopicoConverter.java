package com.nicode.challenge_api_foro_hub.util.converters;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.TopicoEntity;

import java.util.List;

public interface TopicoConverter {

    TopicoResponseDto toTopicoDto(TopicoEntity topicoEntity);
    List<TopicoResponseDto> toTopicoDtoList(List<TopicoEntity> topicoEntities);

    TopicoEntity toTopicoEntity(TopicoRequestDto topico);
}
