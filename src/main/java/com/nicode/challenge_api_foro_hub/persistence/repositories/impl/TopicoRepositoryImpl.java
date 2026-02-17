package com.nicode.challenge_api_foro_hub.persistence.repositories.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoDtoResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;
import com.nicode.challenge_api_foro_hub.persistence.entities.TopicoEntity;
import com.nicode.challenge_api_foro_hub.persistence.repositories.TopicoRepository;
import com.nicode.challenge_api_foro_hub.persistence.repositories.jpa.TopicoJpaRepository;
import com.nicode.challenge_api_foro_hub.util.converters.TopicoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TopicoRepositoryImpl implements TopicoRepository {

    private final TopicoJpaRepository topicoJpaRepository;
    private final TopicoConverter topicoConverter;

    @Override
    public List<TopicoDtoResponse> getAllTopicos() {
        List<TopicoEntity> topicoEntities = topicoJpaRepository.findAll();

        return topicoConverter.toTopicoDtoList(topicoEntities);
    }

    @Override
    public TopicoDtoResponse getTopicoById(Long id) {
        TopicoEntity topicoEntity = topicoJpaRepository.getReferenceById(id);

        return topicoConverter.toTopicoDto(topicoEntity);
    }

    @Override
    public void saveTopico(TopicoDtoRequest topico) {
        TopicoEntity topicoEntity = topicoConverter.toTopicoEntity(topico);

        topicoJpaRepository.save(topicoEntity);
    }

    @Override
    public void updateTopicoById(Long id, TopicoDtoRequest topico) {
        TopicoEntity topicoConverted = topicoConverter.toTopicoEntity(topico);

        TopicoEntity topicoEntity = topicoJpaRepository.getReferenceById(id);

        topicoEntity.setTitulo(topicoConverted.getTitulo());
        topicoEntity.setMensaje(topicoConverted.getMensaje());
        topicoEntity.setFechaCreacion(topicoConverted.getFechaCreacion());
        topicoEntity.setEstado(topicoConverted.getEstado());
        topicoEntity.setCurso(topicoConverted.getCurso());
        topicoEntity.setUsuario(topicoConverted.getUsuario());

        topicoJpaRepository.save(topicoEntity);
    }

    @Override
    public void deleteTopicoById(Long id) {
        topicoJpaRepository.deleteById(id);
    }


    @Override
    public boolean exist(Long id) {
        return topicoJpaRepository.existsById(id);
    }

    @Override
    public boolean existByMensaje(String mensaje) {
        return topicoJpaRepository.existsByMensajeEqualsIgnoreCase(mensaje);
    }

    @Override
    public boolean existByTitulo(String titulo) {
        return topicoJpaRepository.existsByTituloEqualsIgnoreCase(titulo);
    }
}
