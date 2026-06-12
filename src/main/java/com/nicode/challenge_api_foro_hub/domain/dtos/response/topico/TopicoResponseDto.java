package com.nicode.challenge_api_foro_hub.domain.dtos.response.topico;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de respuesta para un tópico", name = "Topico Response")
public class TopicoResponseDto {

    @Schema(description = "Título del tópico", example = "¿Cómo aprender Java?")
    @JsonProperty("Titulo")
    private String titulo;

    @Schema(description = "Mensaje del tópico", example = "Estoy interesado en aprender Java, ¿alguien puede ayudarme?")
    @JsonProperty("Mensaje")
    private String mensaje;

    @Schema(description = "Fecha de creación del tópico", example = "2023-01-01")
    @JsonProperty("Fecha de creacion")
    private String fechaCreacion;

    @Schema(description = "Estado del tópico (abierto o cerrado)", example = "true")
    @JsonProperty("Estado")
    private Boolean estado;

    @Schema(description = "Nombre del autor del tópico", example = "John Doe")
    @JsonProperty("Nombre del autor")
    private String nombreAutor;

    @Schema(description = "Nombre del curso al que pertenece el tópico", example = "Java Básico")
    @JsonProperty("Nombre del curso")
    private String cursoNombre;


}
