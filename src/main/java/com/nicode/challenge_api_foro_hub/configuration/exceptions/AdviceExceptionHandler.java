package com.nicode.challenge_api_foro_hub.configuration.exceptions;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.errors.ErrorResponse;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.errors.MultiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectRetrievalFailureException;
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

        String message = String.format("El parámetro '%s' tiene un valor inválido: '%s'. Se esperaba un valor de tipo '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.BAD_REQUEST.toString(), message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> messages = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            messages.add(error.getDefaultMessage());
        });

        if (messages.size() == 1){
            return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.BAD_REQUEST.toString(), messages.get(0)), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(new MultiErrorResponse("error", HttpStatus.BAD_REQUEST.toString(), messages), HttpStatus.BAD_REQUEST);
        }
    }

//    @ExceptionHandler(JWTVerificationException.class)
//    public ResponseEntity<?> handleJWTVerificationException(JWTVerificationException ex){
//        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.UNAUTHORIZED.toString(), "Verificacion fallida del Token"), HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(TokenExpiredException.class)
//    public ResponseEntity<?> handleTokenExpiredException(TokenExpiredException ex){
//        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.UNAUTHORIZED.toString(), "Token JWT expirado."), HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(JWTDecodeException.class)
//    public ResponseEntity<?> handleJWTDecodeException(JWTDecodeException ex){
//        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.UNAUTHORIZED.toString(), "Error al decodificar el token JWT."), HttpStatus.UNAUTHORIZED);
//    }


    @ExceptionHandler(TopicoNotFoundException.class)
    public ResponseEntity<?> handleTopicoNotFound(TopicoNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.NOT_FOUND.toString(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NombreUsuarioInvalidException.class)
    public ResponseEntity<?> handleUsuarioInvalid(NombreUsuarioInvalidException ex){
        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.UNAUTHORIZED.toString(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ContraseñaInvalidException.class)
    public ResponseEntity<?> handleContraseñaInvalid(ContraseñaInvalidException ex){
        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.UNAUTHORIZED.toString(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<?> handleTokenInvalid(TokenInvalidException ex){
        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.UNAUTHORIZED.toString(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
