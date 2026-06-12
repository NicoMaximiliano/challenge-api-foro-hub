package com.nicode.challenge_api_foro_hub.domain.dtos.request.topico;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.curso.CursoRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoRequestDto {

    @Schema(description = "Título del tópico", example = "¿Cómo aprender Java?")
    @NotBlank(message = "El campo titulo no puede estar vacio")
    private String titulo;

    @Schema(description = "Mensaje del tópico", example = "Estoy interesado en aprender Java, ¿alguien puede ayudarme?")
    @NotBlank(message = "El campo mensaje no puede estar vacio")
    private String mensaje;

    @NotNull(message = "El campo usuario no puede ser nulo")
    @Valid
    private UsuarioRequestDto usuario;

    @NotNull(message = "El campo curso no puede ser nulo")
    @Valid
    private CursoRequestDto curso;


}
