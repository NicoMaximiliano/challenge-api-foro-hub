package com.nicode.challenge_api_foro_hub.domain.dtos.request.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequestDto {

    @NotNull(message = "El campo id no puede ser nulo")
    @Positive(message = "El campo id debe ser un número positivo")
    private Long id;

    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo categoria no puede estar vacio")
    private String categoria;


}
