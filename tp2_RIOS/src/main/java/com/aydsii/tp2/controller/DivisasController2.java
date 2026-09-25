package com.aydsii.tp2.controller;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.HistorialTasaDTO;
import com.aydsii.tp2.model.HistorialConversion;
import com.aydsii.tp2.service.DivisasService2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/divisas")
public class DivisasController2{

    private final DivisasService2 divisasService;

    public DivisasController2(DivisasService2 divisasService){
        this.divisasService = divisasService;
    }
    
    //endpoint POST /api/divisas/consultar?origen=USD&destino=ARS&monto=100
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    @PostMapping("/consultar")
    public ResponseEntity<ApiResponse<HistorialConversion>> consultarCotizacion(
        @RequestParam String origen,
        @RequestParam String destino,
        @RequestParam BigDecimal monto
    ){
        HistorialConversion resultado = divisasService.consultarYGuardar(
            origen.toUpperCase(),
            destino.toUpperCase(),
            monto
        );

        //response
        ApiResponse<HistorialConversion> response = new ApiResponse<>(
                201,
                "Consulta realizada y guardada correctamente",
                resultado
        );
        return ResponseEntity.status(201).body(response);
    }

    //endpoint GET /api/divisas/historial?origen=USD&destino=ARS
    @GetMapping("/historial")
    public ResponseEntity<List<HistorialTasaDTO>> obtenerHistorial(
            @RequestParam String origen,
            @RequestParam String destino) {

        List<HistorialTasaDTO> historial = divisasService.obtenerHistorial(origen, destino);
        
        return ResponseEntity.ok(historial);
    }
}