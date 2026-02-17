package com.nicode.challenge_api_foro_hub.web.controllers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioDtoAuthRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioDtoLoginRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.success.SuccessAuthResponse;
import com.nicode.challenge_api_foro_hub.domain.services.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioDtoLoginRequest userRequest){
        List<String> response = usuarioService.login(userRequest);

        return ResponseEntity.status(200).body(new SuccessAuthResponse(HttpStatus.OK.toString(), response.get(0), response.get(1)));
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioDtoAuthRequest authRequest){
        List<String> response = usuarioService.createUser(authRequest);

        return ResponseEntity.status(201).body(new SuccessAuthResponse(HttpStatus.CREATED.toString(), response.get(0), response.get(1)));
    }


}
