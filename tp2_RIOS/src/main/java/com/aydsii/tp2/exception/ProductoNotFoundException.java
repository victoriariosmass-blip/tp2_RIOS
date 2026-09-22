package com.aydsii.tp2.exception;

public class ProductoNotFoundException extends RuntimeException {
    
    public ProductoNotFoundException(String message) {
        super(message); //le pasa el mensaje de error a la clase padre
    }
}