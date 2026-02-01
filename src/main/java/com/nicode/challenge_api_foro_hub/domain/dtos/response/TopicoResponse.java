package com.nicode.challenge_api_foro_hub.domain.dtos.response;

public record TopicoResponse(
        String titulo,
        String mensaje,
        String fechaCreacion,
        Boolean estado,
        String nombreAutor,
        String cursoNombre
) {
}
