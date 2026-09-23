package com.aydsii.tp2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedidos")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    
    @Column(name = "precio_unitario")
    private Double precioUnitario;

    //muchos detalles a un pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    //muchos detalles pueden apuntar a un mismo producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    public DetallePedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public ProductoEntity getProducto() { return producto; }
    public void setProducto(ProductoEntity producto) { this.producto = producto; }
}