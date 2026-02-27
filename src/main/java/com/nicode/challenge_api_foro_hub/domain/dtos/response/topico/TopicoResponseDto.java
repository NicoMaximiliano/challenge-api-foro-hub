package com.nicode.challenge_api_foro_hub.domain.dtos.response.topico;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoResponseDto {

    @JsonProperty("Titulo")
    private String titulo;

    @JsonProperty("Mensaje")
    private String mensaje;

    @JsonProperty("Fecha de creacion")
    private String fechaCreacion;

    @JsonProperty("Estado")
    private Boolean estado;

    @JsonProperty("Nombre del autor")
    private String nombreAutor;

    @JsonProperty("Nombre del curso")
    private String cursoNombre;


}
