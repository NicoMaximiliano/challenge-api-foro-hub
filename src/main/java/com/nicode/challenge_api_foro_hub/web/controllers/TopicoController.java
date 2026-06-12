package com.nicode.challenge_api_foro_hub.web.controllers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.topico.TopicoResponseDto;
import com.nicode.challenge_api_foro_hub.domain.services.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos", description = "Operaciones relacionadas con los tópicos del foro")
public class TopicoController {

    private final TopicoService topicoService;


    @Operation(summary = "Obtener todos los tópicos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tópicos obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TopicoResponseDto.class))) }),
            @ApiResponse(responseCode = "204", description = "No hay tópicos registrados en el sistema", content = @Content(mediaType = "application/json")) })
    @GetMapping("/all")
    public ResponseEntity<?> getAllTopicos() {
        if (topicoService.getAllTopicos().isEmpty()){
            ResponseDto response = new ResponseDto(204, "No Content" , "No se hay topicos registrados en el sistema");
            return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
        }
        return ResponseEntity.status(200).body(topicoService.getAllTopicos());
    }


    @Operation(summary = "Obtener un tópico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tópico obtenido exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TopicoResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, ID no válido", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado con el ID proporcionado", content = @Content(mediaType = "application/json")) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicoById(@Parameter(description = "ID del tópico a obtener", required = true, example = "1", schema = @Schema(implementation = String.class)) @PathVariable Long id) {
        return ResponseEntity.status(200).body(topicoService.getTopicoById(id));
    }


    @Operation(summary = "Crear un nuevo tópico en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tópico creado exitosamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, datos de entrada no válidos", content = @Content(mediaType = "application/json")) })
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createTopico(@RequestBody @Valid TopicoRequestDto topico) {
        ResponseDto response = topicoService.saveNewTopico(topico);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }


    @Operation(summary = "Actualizar un tópico existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tópico actualizado exitosamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, datos de entrada no válidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado con el ID proporcionado", content = @Content(mediaType = "application/json")) })
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateTopico(@Parameter(description = "ID del tópico a actualizar", required = true, example = "1", schema = @Schema(implementation = String.class)) @PathVariable Long id, @RequestBody @Valid TopicoRequestDto topico) {
        ResponseDto response = topicoService.updateTopico(id, topico);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }


    @Operation(summary = "Eliminar un tópico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tópico eliminado exitosamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, datos de entrada no válidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado con el ID proporcionado", content = @Content(mediaType = "application/json")) })
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopico(@Parameter(description = "ID del tópico a eliminar", required = true, example = "1", schema = @Schema(implementation = String.class)) @PathVariable Long id) {
        ResponseDto response = topicoService.deleteTopicoById(id);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

}
