package com.aydsii.tp2.dto;

import java.util.List;

public class ResultadoDescuentoDTO {
    private List<VentaConDescuentoDTO> ventas;
    private Double totalConDescuento;

    //constructor
    public ResultadoDescuentoDTO() {}

    //setters, getters
    public List<VentaConDescuentoDTO> getVentas() { 
        return ventas; 
    }
    public void setVentas(List<VentaConDescuentoDTO> ventas) { 
        this.ventas = ventas; 
    }

    public Double getTotalConDescuento() { 
        return totalConDescuento; 
    }
    public void setTotalConDescuento(Double totalConDescuento) { 
        this.totalConDescuento = totalConDescuento; 
    }
}