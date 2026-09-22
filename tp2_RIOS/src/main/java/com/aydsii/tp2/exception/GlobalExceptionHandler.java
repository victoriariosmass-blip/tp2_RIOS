package com.aydsii.tp2.exception;

import com.aydsii.tp2.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> manejarErrorValidacion(MethodArgumentNotValidException ex) {
        
        FieldError error = ex.getBindingResult().getFieldErrors().get(0);
        String campoYPosicion = error.getField(); 
        String motivo = error.getDefaultMessage();
        
        String mensajeFinal = "Error de validación en " + campoYPosicion + ": " + motivo;
        
        ApiResponse<Void> response = new ApiResponse<>(400, mensajeFinal, null);
        
        return ResponseEntity.status(400).body(response);
    }

    //recursos no encontrados
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> manejarProductoNoEncontrado(ProductoNotFoundException ex) {
        
        ApiResponse<Void> response = new ApiResponse<>(404, ex.getMessage(), null);
        return ResponseEntity.status(404).body(response); 
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        
        ApiResponse<Void> response = new ApiResponse<>(
                400, // bad request
                ex.getMessage(), // stock insuficiente
                null 
        );
        return ResponseEntity.status(400).body(response);
    }
}