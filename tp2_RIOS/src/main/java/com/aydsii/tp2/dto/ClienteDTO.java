package com.aydsii.tp2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO{
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String apellido;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser valido")
    private String email;
    @Pattern(regexp = "^[0-9]*$", message = "El teléfono solo debe contener dígitos")
    private String telefono;

    //constructores
    public ClienteDTO(){}

    public ClienteDTO(String nombre, String apellido, String email, String telefono){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    //setters, getters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    public String getApellido(){
        return apellido;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public String getTelefono(){
        return telefono;
    }
}

/*{
"nombre": "Ana",
"apellido": "Garcia",
"email": "ana.garcia@mail.com",
"telefono": "3814567890"
} */