package com.integrador.grupoA.ExceptionHandler;

import com.integrador.grupoA.DTO.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    public static class ViajeNotFoundException extends RuntimeException {
        public ViajeNotFoundException(String message) {
            super(message);
        }
    }

    public static class ViajeCreationException extends RuntimeException {
        public ViajeCreationException(String message) {
            super(message);
        }
    }

    public static class ViajeUpdateException extends RuntimeException {
        public ViajeUpdateException(String message) {
            super(message);
        }
    }

    public static class ViajeDeletionException extends RuntimeException {
        public ViajeDeletionException(String message) {
            super(message);
        }
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleExpiredToken(ExpiredJwtException ex) {
        final String errorMessage = "Error: El token JWT no es valido o ha expirado.";
        final ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), errorMessage, System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(AccessDeniedException ex) {
        final String errorMessage = "Error: No tienes permiso para acceder a este recurso.";
        final ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.FORBIDDEN.value(), errorMessage, System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}