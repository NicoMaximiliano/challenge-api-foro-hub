package com.nicode.challenge_api_foro_hub.persistence.repositories.impl;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;
import com.nicode.challenge_api_foro_hub.persistence.entities.TopicoEntity;
import com.nicode.challenge_api_foro_hub.persistence.repositories.TopicoRepository;
import com.nicode.challenge_api_foro_hub.persistence.repositories.jpa.CursoJpaRepository;
import com.nicode.challenge_api_foro_hub.persistence.repositories.jpa.TopicoJpaRepository;
import com.nicode.challenge_api_foro_hub.persistence.repositories.jpa.UsuarioJpaRepository;
import com.nicode.challenge_api_foro_hub.util.converters.TopicoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TopicoRepositoryImpl implements TopicoRepository {

    private final TopicoJpaRepository topicoJpaRepository;
    private final CursoJpaRepository cursoJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final TopicoConverter topicoConverter;

    @Override
    public List<TopicoResponseDto> getAllTopicos() {
        List<TopicoEntity> topicoEntities = topicoJpaRepository.findAll();

        return topicoConverter.toTopicoDtoList(topicoEntities);
    }

    @Override
    public TopicoResponseDto getTopicoById(Long id) {
        TopicoEntity topicoEntity = topicoJpaRepository.getReferenceById(id);

        return topicoConverter.toTopicoDto(topicoEntity);
    }

    @Override
    public void saveTopico(TopicoRequestDto topico) {
        TopicoEntity topicoEntity = topicoConverter.toTopicoEntity(topico);

        topicoJpaRepository.save(topicoEntity);
    }

    @Override
    public void updateTopicoById(Long id, TopicoRequestDto topico) {
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
    public boolean existByTituloOrMensaje(TopicoRequestDto topico) {
        String titulo = topico.getTitulo();
        String mensaje = topico.getMensaje();

        return topicoJpaRepository.existsByTituloOrMensaje(titulo, mensaje);
    }

    @Override
    public boolean existCurso(TopicoRequestDto topico) {
        Long idCurso = topico.getCurso().getId();
        String nombreCurso = topico.getCurso().getNombre();
        String categoriaCurso = topico.getCurso().getCategoria();

        return cursoJpaRepository.existsByIdAndNombreAndCategoria(idCurso, nombreCurso, categoriaCurso);
    }

    @Override
    public boolean existUsuario(TopicoRequestDto topico) {
        Long idUsuario = topico.getUsuario().getId();
        String nombreUsuario = topico.getUsuario().getNombre();
        String emailUsuario = topico.getUsuario().getEmail();

        return usuarioJpaRepository.existsByIdAndNombreAndEmail(idUsuario, nombreUsuario, emailUsuario);
    }


}
