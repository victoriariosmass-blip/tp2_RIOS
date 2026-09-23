package com.aydsii.tp2.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoResponseDTO {
    private Long pedidoId;
    private String cliente;
    private LocalDate fechaPedido;
    private String estado;
    private Double totalPedido;
    private List<ProductoPedidoDTO> productos;

    public PedidoResponseDTO() {}

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public LocalDate getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDate fechaPedido) { this.fechaPedido = fechaPedido; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Double getTotalPedido() { return totalPedido; }
    public void setTotalPedido(Double totalPedido) { this.totalPedido = totalPedido; }
    public List<ProductoPedidoDTO> getProductos() { return productos; }
    public void setProductos(List<ProductoPedidoDTO> productos) { this.productos = productos; }
}