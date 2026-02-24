package com.nicode.challenge_api_foro_hub.persistence.repositories;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;

import java.util.List;

public interface TopicoRepository {

    List<TopicoResponseDto> getAllTopicos();
    TopicoResponseDto getTopicoById(Long id);
    void saveTopico(TopicoRequestDto topico);
    void updateTopicoById(Long id, TopicoRequestDto topico);
    void deleteTopicoById(Long id);

    boolean exist(Long id);
    boolean existByTituloOrMensaje(TopicoRequestDto topico);
    boolean existCurso(TopicoRequestDto topico);
    boolean existUsuario(TopicoRequestDto topico);
}
