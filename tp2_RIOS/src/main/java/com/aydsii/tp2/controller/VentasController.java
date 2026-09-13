package com.techstore.controller;

import com.techstore.dto.ApiResponse;
import com.techstore.dto.EstadisticasVentasDTO;
import com.techstore.dto.VentaDTO;
import com.techstore.service.VentasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@Validated
public class Controller {
    private final VentasService VentasService;

    //constructor con inyeccion de dependencia
    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    //doc swagger
    @Operation(
            summary = "Calcular estadísticas de ventas",
            description = "Recibe un lote de ventas y devuelve cálculos como el total 
            facturado, ticket promedio, venta mayor y menor, y producto más vendido."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", 
                    description = "Estadísticas calculadas con éxito"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", 
                    description = "Datos inválidos o lista vacía"
            )
    })

    //endpoint
    @PostMapping("/estadisticas")
    public ResponseEntity<ApiResponse<EstadisticasVentasDTO>> calcularEstadisticas(
            @RequestBody 
            @NotEmpty(message = "La lista no puede venir vacía") 
            List<@Valid VentaDTO> ventas) {
                EstadisticasVentasDTO estadisticas = ventasService.procesarEstadisticas(ventas);
            //rta estandar
            ApiResponse<EstadisticasVentasDTO> response = new ApiResponse<>(
                200,
                "Operacion realizada con exito", // Sin tilde, calcado a como está en la consigna
                estadisticas
        );
        //devolucion 200 ok
        return ResponseEntity.ok(response);
    }
}