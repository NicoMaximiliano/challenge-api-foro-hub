package com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginRequestDto {

    @Schema(description = "Nombre del usuario", example = "John Doe")
    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @Schema(description = "Contraseña del usuario", example = "P@ssw0rd")
    @NotBlank(message = "El campo contraseña no puede estar vacio")
    private String contrasenia;


}
