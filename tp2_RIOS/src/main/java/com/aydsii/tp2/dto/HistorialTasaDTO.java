package com.aydsii.tp2.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistorialTasaDTO {

    private LocalDateTime fecha;
    private BigDecimal tasaCambio;

    //constructor
    public HistorialTasaDTO() {}

    //setters y getters
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public BigDecimal getTasaCambio() { return tasaCambio; }
    public void setTasaCambio(BigDecimal tasaCambio) { this.tasaCambio = tasaCambio; }
}