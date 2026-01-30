package com.nicode.challenge_api_foro_hub.domain.dtos.request;

public record TopicoRequest(
        String titulo,
        String mensaje,
        String nombreAutor,
        String cursoNombre
) {
}
