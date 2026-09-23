package com.aydsii.tp2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes") //indicar la tabla
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generacion automatica del id
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    @Column(name = "fecha_registro") //vinculacion atributo a nombre de columna
    private LocalDateTime fechaRegistro;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String email, String telefono, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    //getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}