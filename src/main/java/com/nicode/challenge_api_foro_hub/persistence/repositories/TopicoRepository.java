package com.nicode.challenge_api_foro_hub.persistence.repositories;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoDtoResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;

import java.util.List;

public interface TopicoRepository {

    List<TopicoDtoResponse> getAllTopicos();
    TopicoDtoResponse getTopicoById(Long id);
    void saveTopico(TopicoDtoRequest topico);
    void updateTopicoById(Long id, TopicoDtoRequest topico);
    void deleteTopicoById(Long id);

    boolean exist(Long id);
    boolean existByMensaje(String mensaje);
    boolean existByTitulo(String titulo);
}
