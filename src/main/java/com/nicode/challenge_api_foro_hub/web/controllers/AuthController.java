package com.nicode.challenge_api_foro_hub.web.controllers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioLoginRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.services.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Operaciones relacionadas con la autenticación de usuarios")
public class AuthController {

    private final UsuarioServiceImpl usuarioService;

    @Operation(summary = "  Iniciar sesión y obtener un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso, token JWT generado", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Datos de inicio incompletos, error en la solicitud", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas, inicio de sesión fallido", content = {
                    @Content(mediaType = "application/json") }) })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioLoginRequestDto userRequest){
        ResponseDto response = usuarioService.login(userRequest);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponseWithToken());
    }


    @Operation(summary = "Registrar un nuevo usuario y obtener un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente, token JWT generado", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Datos de registro inválidos, error en la solicitud", content = {
                    @Content(mediaType = "application/json") }) })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioAuthRequestDto authRequest){
        ResponseDto response = usuarioService.createUser(authRequest);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponseWithToken());
    }

}
