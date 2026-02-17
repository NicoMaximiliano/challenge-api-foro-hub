package com.nicode.challenge_api_foro_hub.web.controllers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoDtoRequest;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.success.SuccessTopicoResponse;
import com.nicode.challenge_api_foro_hub.domain.services.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTopicos() {
        if (topicoService.getAllTopicos().isEmpty()){
            return ResponseEntity.status(200).body(new SuccessTopicoResponse(HttpStatus.OK.toString(), "No se hay topicos registrados en el sistema"));
        }
        return ResponseEntity.status(200).body(topicoService.getAllTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicoById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(topicoService.getTopicoById(id));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createTopico(@RequestBody @Valid TopicoDtoRequest topico) {
        return ResponseEntity.status(201).body(new SuccessTopicoResponse(HttpStatus.CREATED.toString(), topicoService.saveNewTopico(topico)));
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateTopico(@PathVariable Long id, @RequestBody @Valid TopicoDtoRequest topico) {
        return ResponseEntity.status(200).body(new SuccessTopicoResponse(HttpStatus.OK.toString(), topicoService.updateTopico(id, topico)));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new SuccessTopicoResponse(HttpStatus.OK.toString(), topicoService.deleteTopicoById(id)));
    }

}
