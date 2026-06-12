package com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(description = "DTO de solicitud para un usuario", name = "Usuario Request")
public class UsuarioRequestDto {

    @Schema(description = "ID del usuario", example = "1")
    @NotNull(message = "El campo id no puede ser nulo")
    @Positive(message = "El campo id debe ser un número positivo")
    private Long id;

    @Schema(description = "Nombre del usuario", example = "John Doe")
    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @Schema(description = "Email del usuario", example = "john.doe@example.com")
    @NotBlank(message = "El campo email no puede estar vacio")
    @Email(message = "El campo email debe ser una dirección de correo electrónico válida")
    private String email;


}
