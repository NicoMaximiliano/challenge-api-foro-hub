package com.nicode.challenge_api_foro_hub.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NombreUsuarioInvalidException extends RuntimeException {
    public NombreUsuarioInvalidException(String message) {
        super(message);
    }
}
