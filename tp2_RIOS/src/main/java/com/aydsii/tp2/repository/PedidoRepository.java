package com.aydsii.tp2.repository;

import com.aydsii.tp2.model.Pedido;
import com.aydsii.tp2.model.EstadoPedido; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
//si alguno de los parametros viene vacio se ignora, permite opcionalidad
//distinct para que cada pedido venga una sola vez (no repeticion)
    @Query("""
        SELECT DISTINCT p
        FROM Pedido p
        JOIN p.cliente c
        JOIN p.detalles d
        JOIN d.producto pr
        JOIN pr.categoria cat
        WHERE (c.id = :clienteId OR :clienteId IS NULL)
        AND (cat.nombre = :categoria OR :categoria IS NULL)
        AND (p.fechaPedido >= :fechaDesde OR :fechaDesde IS NULL)
        AND (p.fechaPedido <= :fechaHasta OR :fechaHasta IS NULL)
        AND (p.estado = :estado OR :estado IS NULL)
    """)
    List<Pedido> buscarPedidos(
            @Param("clienteId") Long clienteId,
            @Param("categoria") String categoria,
            @Param("fechaDesde") LocalDate fechaDesde,
            @Param("fechaHasta") LocalDate fechaHasta,
            @Param("estado") EstadoPedido estado
    );
}