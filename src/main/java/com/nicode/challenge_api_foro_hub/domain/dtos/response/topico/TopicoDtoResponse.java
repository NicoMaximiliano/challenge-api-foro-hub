package com.nicode.challenge_api_foro_hub.domain.dtos.response.topico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDtoResponse {
    private String titulo;
    private String mensaje;
    private String fechaCreacion;
    private Boolean estado;
    private String nombreAutor;
    private String cursoNombre;
}
