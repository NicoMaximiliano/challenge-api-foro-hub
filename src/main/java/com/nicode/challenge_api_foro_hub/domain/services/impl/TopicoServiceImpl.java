package com.nicode.challenge_api_foro_hub.domain.services.impl;

import com.nicode.challenge_api_foro_hub.web.exceptions.TopicoNotFoundException;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;
import com.nicode.challenge_api_foro_hub.domain.services.TopicoService;
import com.nicode.challenge_api_foro_hub.persistence.repositories.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;


    @Override
    public List<TopicoResponseDto> getAllTopicos() {
        return topicoRepository.getAllTopicos();
    }

    @Override
    public TopicoResponseDto getTopicoById(Long id) {
        if (topicoRepository.exist(id)) {
            return topicoRepository.getTopicoById(id);
        }

        throw new TopicoNotFoundException("El topico con ID " + id + " no existe. No se pudo encontrar el Tópico.");
    }

    @Override
    public ResponseDto saveNewTopico(TopicoRequestDto topico) {
        if (!topicoRepository.existByTituloOrMensaje(topico)) {
            if(topicoRepository.existCurso(topico) && topicoRepository.existUsuario(topico)){
                topicoRepository.saveTopico(topico);
                return new ResponseDto(201, "Exito" , "Tópico creado con éxito");
            }
            else{
                return new ResponseDto(400, "Error" , "No se pudo crear el Tópico. Verifica que el curso y usuario existan.");
            }
        }
        return new ResponseDto(400, "Error" , "No se pudo crear el Tópico. Verifica que el título o mensaje no existan.");
    }

    @Override
    public ResponseDto updateTopico(Long id, TopicoRequestDto topico) {
        if (topicoRepository.exist(id)) {
            if (!topicoRepository.existByTituloOrMensaje(topico)) {
                if(topicoRepository.existCurso(topico) && topicoRepository.existUsuario(topico)){
                    topicoRepository.updateTopicoById(id, topico);
                    return new ResponseDto(200, "Exito" , "Tópico actualizado con éxito");
                }
                else{
                    return new ResponseDto(400, "Error" , "No se pudo actualizar el Tópico. Verifica que el curso y usuario existan.");
                }
            }
            return new ResponseDto(400, "Error" , "No se pudo actualizar el Tópico. Verifica que el título o mensaje no se repitan.");
        }

        throw new TopicoNotFoundException("El topico con ID " + id + " no existe. No se pudo actualizar el Tópico.");
    }

    @Override
    public ResponseDto deleteTopicoById(Long id) {
        if (topicoRepository.exist(id)) {
            topicoRepository.deleteTopicoById(id);
            return new ResponseDto(200, "Exito" , "Tópico eliminado con éxito");
        }
        else{
            throw new TopicoNotFoundException("El topico con ID " + id + " no existe. No se pudo eliminar el Tópico.");
        }
    }


}
