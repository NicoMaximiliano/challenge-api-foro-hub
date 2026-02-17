package com.nicode.challenge_api_foro_hub.domain.services;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoDtoResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;

import java.util.List;

public interface TopicoService {

    List<TopicoDtoResponse> getAllTopicos();
    TopicoDtoResponse getTopicoById(Long id);
    String saveNewTopico(TopicoDtoRequest topico);
    String updateTopico(Long id, TopicoDtoRequest topico);
    String deleteTopicoById(Long id);
}
