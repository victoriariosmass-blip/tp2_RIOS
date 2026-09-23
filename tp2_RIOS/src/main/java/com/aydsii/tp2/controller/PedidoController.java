package com.aydsii.tp2.controller;

import com.aydsii.tp2.dto.ApiResponse; 
import com.aydsii.tp2.dto.PedidoResponseDTO;
import com.aydsii.tp2.model.EstadoPedido;
import com.aydsii.tp2.service.PedidoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<PedidoResponseDTO>>> buscarPedidos(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) EstadoPedido estado) {

        List<PedidoResponseDTO> respuesta = pedidoService.buscarPedidos(
                clienteId,
                categoria,
                fechaDesde,
                fechaHasta,
                estado
        );

        ApiResponse<List<PedidoResponseDTO>> response = new ApiResponse<>(
                200,
                "Consulta realizada correctamente",
                respuesta //si la lista es vacía, "data": [] 
        );

        return ResponseEntity.ok(response);
    }
}