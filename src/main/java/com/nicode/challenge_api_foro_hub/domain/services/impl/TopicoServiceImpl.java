package com.nicode.challenge_api_foro_hub.domain.services.impl;

import com.nicode.challenge_api_foro_hub.configuration.exceptions.TopicoNotFoundException;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoDtoResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;
import com.nicode.challenge_api_foro_hub.domain.services.TopicoService;
import com.nicode.challenge_api_foro_hub.persistence.repositories.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;


    @Override
    public List<TopicoDtoResponse> getAllTopicos() {
        return topicoRepository.getAllTopicos();
    }

    @Override
    public TopicoDtoResponse getTopicoById(Long id) {
        if (topicoRepository.exist(id)) {
            return topicoRepository.getTopicoById(id);
        }
        throw new TopicoNotFoundException("El topico solicitado no existe");
    }

    @Override
    public String saveNewTopico(TopicoDtoRequest topico) {
        if (!topicoRepository.existByTitulo(topico.getTitulo()) && !topicoRepository.existByMensaje(topico.getMensaje())) {
            topicoRepository.saveTopico(topico);

            return "Tópico creado con éxito";
        }
        return "No se pudo crear el Tópico. El título o mensaje del tópico ya existe";
    }

    @Override
    public String updateTopico(Long id, TopicoDtoRequest topico) {
        if (topicoRepository.exist(id)) {
            if (!topicoRepository.existByTitulo(topico.getTitulo()) && !topicoRepository.existByMensaje(topico.getMensaje())) {
                topicoRepository.updateTopicoById(id, topico);

                return "Tópico actualizado con éxito";
            }

            return "No se pudo actualizar el Tópico. El título o mensaje del tópico ya existe";
        }

        throw new TopicoNotFoundException("El ID del Tópico a actualizar no existe");
    }

    @Override
    public String deleteTopicoById(Long id) {
        if (topicoRepository.exist(id)) {
            topicoRepository.deleteTopicoById(id);

            return "Tópico eliminado con éxito";
        }

       throw new TopicoNotFoundException("El ID del Tópico a eliminar no existe");
    }


}
