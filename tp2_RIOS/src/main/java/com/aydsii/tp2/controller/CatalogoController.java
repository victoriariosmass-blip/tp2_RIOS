package com.aydsii.tp2.controller;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.ProductoDTO;
import com.aydsii.tp2.model.Producto;
import com.aydsii.tp2.service.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {
    private final CatalogoService catalogoService;
    
    //inyeccion de depndencias
    public CatalogoController(CatalogoService catalogoService){
        this catalogoService = catalogoService;
    } 

    //endpoint get
    @Operation(summary = "Obtener todos los productos del catálogo")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> obtenerCatalogo(){
        List<Producto> productosModel = catalogoService.obtenerTodos();

        List<ProductoDTO> productosDTO = new ArrayList<>();

        //convertir cada producto en productoDTO
        for (Producto p : productosModel){
            ProductoDTO dto = new ProductoDTO(p.getId, p.getCategoria, p.getNombre, p.getCategoria, p.getPrecio, p.getStock);
            productosDTO.add(dto);
        }

        //respuesta
        ApiResponse<List<ProductoDTO>> response = new ApiResponse<>(
                200, 
                "Operacion realizada con exito", 
                productosDTO
        );

        return ResponseEntity.ok(response);
    }
}