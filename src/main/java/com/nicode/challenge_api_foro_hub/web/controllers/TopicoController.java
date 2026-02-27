package com.nicode.challenge_api_foro_hub.web.controllers;

import com.nicode.challenge_api_foro_hub.domain.dtos.request.topico.TopicoRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.services.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTopicos() {
        if (topicoService.getAllTopicos().isEmpty()){
            ResponseDto response = new ResponseDto(200, "Ok" , "No se hay topicos registrados en el sistema");
            return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
        }
        return ResponseEntity.status(200).body(topicoService.getAllTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicoById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(topicoService.getTopicoById(id));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createTopico(@RequestBody @Valid TopicoRequestDto topico) {
        ResponseDto response = topicoService.saveNewTopico(topico);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateTopico(@PathVariable Long id, @RequestBody @Valid TopicoRequestDto topico) {
        ResponseDto response = topicoService.updateTopico(id, topico);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) {
        ResponseDto response = topicoService.deleteTopicoById(id);
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

}
