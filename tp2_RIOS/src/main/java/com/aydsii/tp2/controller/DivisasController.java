package com.aydsii.tp2.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 
import com.aydsii.tp2.dto.ConversionResponseDTO;
import com.aydsii.tp2.service.DivisasService;
import com.aydsii.tp2.dto.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/divisas")
@Validated
public class DivisasController{
    private final DivisasService divisasService;
    //iyeccion de dependencias
    public DivisasController(DivisasService divisasService){
        this.divisasService = divisasService;
    }

    @GetMapping("/convertir")
    public ResponseEntity<ApiResponse<ConversionResponseDTO>> convertirDivisa(
        @RequestParam @Positive(message = "El monto debe ser mayor a 0") Double monto,
        @RequestParam @Size(min = 3, max = 3, message = "El origen debe tener 3 letras") String origen,
        @RequestParam @Size(min = 3, max = 3, message = "El destino debe tener 3 letras") String destino
    ){
        //delegar a service
        ConversionResponseDTO resultado = divisasService.convertir(monto, origen.toUpperCase(), destino.toUpperCase());

        //response
        ApiResponse<ConversionResponseDTO> response = new ApiResponse<>(
                200, "Conversion exitosa", resultado
        );
        return ResponseEntity.ok(response);
    }
}