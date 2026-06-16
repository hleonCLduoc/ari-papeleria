package com.aripapeleria.ms_pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Atrapa la falta de stock y validaciones (400 Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMensaje("Error de validación de negocio");
        error.setDetalle(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 2. Atrapa si el producto no existe o falla Feign (404 Not Found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMensaje("Recurso no encontrado");
        error.setDetalle(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}