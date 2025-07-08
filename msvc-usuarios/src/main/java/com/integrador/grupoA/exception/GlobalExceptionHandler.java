package com.integrador.grupoA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(AccessDeniedException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Acceso denegado.", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ha ocurrido un error inesperado.", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}