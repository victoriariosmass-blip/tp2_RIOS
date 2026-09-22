package com.aydsii.tp2.dto;

public class VentaConDescuentoDTO {
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double montoConDescuento; 

    //constructor
    public VentaConDescuentoDTO() {}

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

    public Double getMontoConDescuento() { 
        return montoConDescuento; 
    }
    public void setMontoConDescuento(Double montoConDescuento) { 
        this.montoConDescuento = montoConDescuento; 
    }
}