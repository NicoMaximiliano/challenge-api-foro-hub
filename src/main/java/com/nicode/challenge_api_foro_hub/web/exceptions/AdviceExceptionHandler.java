package com.nicode.challenge_api_foro_hub.web.exceptions;

import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex){

        String mensaje = String.format("El parámetro '%s' tiene un valor inválido: '%s'. Se esperaba un valor de tipo '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ResponseDto response = new ResponseDto(400, "Bad Request", mensaje);

        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException ex) {
        ResponseDto response;
        List<String> mensajes = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            mensajes.add(error.getDefaultMessage());
        });

        if (mensajes.size() == 1){
            response = new ResponseDto(400, "Bad Request", mensajes.get(0));
            return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
        }
        else{
            response = new ResponseDto(400, "Bad Request", mensajes);
            return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
        }
    }

    @ExceptionHandler(TopicoNotFoundException.class)
    public ResponseEntity<?> handleTopicoNotFound(TopicoNotFoundException ex){
        ResponseDto response = new ResponseDto(404, "Not Found", ex.getMessage());
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

    @ExceptionHandler(ContraseniaInvalidException.class)
    public ResponseEntity<?> handleContraseñaInvalid(ContraseniaInvalidException ex){
        ResponseDto response = new ResponseDto(401, "Unauthorized", ex.getMessage());
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }

    @ExceptionHandler(NombreUsuarioInvalidException.class)
    public ResponseEntity<?> handleNombreUsuarioInvalid(NombreUsuarioInvalidException ex){
        ResponseDto response = new ResponseDto(401, "Unauthorized", ex.getMessage());
        return ResponseEntity.status(response.getCodigo()).body(response.showResponse());
    }


}
