package com.aydsii.tp2.exception;

import com.aydsii.tp2.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

    //divisas excepciones para api frankfurter
    @ExceptionHandler(MonedaNoSoportadaException.class)
    public ResponseEntity<ApiResponse<Void>> handleMonedaNoSoportada(MonedaNoSoportadaException ex) {
        
        ApiResponse<Void> response = new ApiResponse<>(
                400, //bad Request
                ex.getMessage(), 
                null
        );
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(ExternalApiConnectionException.class)
    public ResponseEntity<ApiResponse<Void>> handleExternalApiConnection(ExternalApiConnectionException ex) {
        
        ApiResponse<Void> response = new ApiResponse<>(
                502, //bad Gateway
                ex.getMessage(), 
                null
        );
        return ResponseEntity.status(502).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
        //obtenemos el primer error en la lista de validaciones que fallaron
        FieldError primerError = ex.getBindingResult().getFieldErrors().get(0);
        
        //extraemos el mensaje del DTO 
        String mensajeError = primerError.getDefaultMessage();

        ApiResponse<Void> response = new ApiResponse<>(
                400, 
                mensajeError, 
                null
        );
        return ResponseEntity.status(400).body(response);
    }
}