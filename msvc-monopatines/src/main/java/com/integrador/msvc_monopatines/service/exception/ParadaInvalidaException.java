package com.integrador.msvc_monopatines.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParadaInvalidaException extends RuntimeException {
    public ParadaInvalidaException(String message) {
        super(message);
    }
}
