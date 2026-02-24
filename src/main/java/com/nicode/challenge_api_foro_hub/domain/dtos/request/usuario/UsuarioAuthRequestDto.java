package com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAuthRequestDto {

    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo email no puede estar vacio")
    @Email(message = "El campo email debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "El campo contraseña no puede estar vacio")
    private String contrasenia;
}
