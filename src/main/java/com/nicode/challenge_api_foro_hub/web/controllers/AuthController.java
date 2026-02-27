package com.nicode.challenge_api_foro_hub.web.controllers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioLoginRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.services.impl.UsuarioServiceImpl;
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
public class AuthController {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioLoginRequestDto userRequest){
        ResponseDto response = usuarioService.login(userRequest);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponseWithToken());
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioAuthRequestDto authRequest){
        ResponseDto response = usuarioService.createUser(authRequest);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponseWithToken());
    }

}
