package com.integrador.grupoA.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class ErrorResponseDTO {
    private int status;
    private String message;
    private long timestamp;
}