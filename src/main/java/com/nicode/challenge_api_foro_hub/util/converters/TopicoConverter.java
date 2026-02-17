package com.nicode.challenge_api_foro_hub.util.converters;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoDtoResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;
import com.nicode.challenge_api_foro_hub.persistence.entities.TopicoEntity;

import java.util.List;

public interface TopicoConverter {

    TopicoDtoResponse toTopicoDto(TopicoEntity topicoEntity);
    List<TopicoDtoResponse> toTopicoDtoList(List<TopicoEntity> topicoEntities);

    TopicoEntity toTopicoEntity(TopicoDtoRequest topico);
}
