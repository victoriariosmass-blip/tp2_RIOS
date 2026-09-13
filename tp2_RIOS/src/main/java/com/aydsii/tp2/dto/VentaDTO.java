package com.techstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VentaDTO {
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String producto;

    @Positive(message = "La cantidad debe ser mayor a cero")
    @NotNull(message = "La cantidad es obligatoria")
    private int cantidad;

    @Positive(message = "El precio debe ser mayor a cero")
    @NotNull(message = "El precio es obligatorio")
    private double precioUnitario;

    //constructores
    public VentaDTO() {}

    public VentaDTO(String producto, Integer cantidad, Double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    //setters, getters
    public String getProducto() { 
        return producto; 
    }
    public void setProducto(String producto) { 
        this.producto = producto; 
    }

    public Integer getCantidad() { 
        return cantidad;
    }
    public void setCantidad(Integer cantidad) { 
        this.cantidad = cantidad; 
    }

    public Double getPrecioUnitario() { 
        return precioUnitario; 
    }
    public void setPrecioUnitario(Double precioUnitario) { 
        this.precioUnitario = precioUnitario; 
    }

}