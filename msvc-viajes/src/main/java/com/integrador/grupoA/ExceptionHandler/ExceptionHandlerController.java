package com.integrador.grupoA.ExceptionHandler;

import org.springframework.http.ResponseEntity;
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
}