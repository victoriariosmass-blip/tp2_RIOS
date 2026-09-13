package com.techstore.dto;

public class EstadisticasVentasDTO {
    private double totalFacturado;
    private int cantidadVentas;
    private double ticketPromedio;
    private VentaDTO ventaMayor;
    private VentaDTO ventaMenor;
    private String productoMasVendido;
}

//constructor 
public EstadisticasVentasDTO(){}

//setters, getters
public void setTotalFacturado(double totalFacturado) {
    this.totalFacturado = totalFacturado;
}
public double getTotalFacturado(){
    return totalFacturado;
}

public void setCantidadVentas(int cantidadVentas) {
    this.cantidadVentas = cantidadVentas;
}
public int getCantidadVentas(){
    return cantidadVentas;
}

public void setTicketPromedio(double ticketPromedio) {
    this.ticketPromedio = ticketPromedio
}
public double getTicketPromedio(){
    return ticketPromedio;
}

public void setVentaMayor(VentaDTO ventaMayor) {
    this.ventaMayor = ventaMayor;
}
public VentaDTO getVentaMayor(){
    return ventaMayor;
}

public void setVentaMenor(VentaDTO ventaMenor) {
    this.ventaMayor = ventaMenor;
}
public VentaDTO getVentaMenor(){
    return ventaMenor;
}

public void setProductoMasVendido(String productoMasVendido){
    this.productoMasVendido = productoMasVendido;
}
public String getProductoMasVendido(){
    return productoMasVendido;
}

