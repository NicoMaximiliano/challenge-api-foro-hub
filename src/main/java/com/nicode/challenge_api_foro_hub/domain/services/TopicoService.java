package com.nicode.challenge_api_foro_hub.domain.services;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;

import java.util.List;

public interface TopicoService {

    List<TopicoResponseDto> getAllTopicos();
    TopicoResponseDto getTopicoById(Long id);
    ResponseDto saveNewTopico(TopicoRequestDto topico);
    ResponseDto updateTopico(Long id, TopicoRequestDto topico);
    ResponseDto deleteTopicoById(Long id);

}
