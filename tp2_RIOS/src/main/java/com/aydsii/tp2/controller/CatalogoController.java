package com.aydsii.tp2.controller;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.ProductoDTO;
import com.aydsii.tp2.model.Producto;
import com.aydsii.tp2.service.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {
    private final CatalogoService catalogoService;
    
    //inyeccion de depndencias
    public CatalogoController(CatalogoService catalogoService){
        this.catalogoService = catalogoService;
    } 

    //endpoint get
    @Operation(summary = "Obtener todos los productos del catálogo")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> obtenerCatalogo(){
        List<Producto> productosModel = catalogoService.obtenerTodos();

        List<ProductoDTO> productosDTO = new ArrayList<>();

        //convertir cada producto en productoDTO
        for (Producto p : productosModel){
            ProductoDTO dto = new ProductoDTO(p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock());
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

    @Operation(summary = "Buscar y filtrar productos")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> buscarCatalogo(
        //parametros opcionales
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) Double precioMin,
        @RequestParam(required = false) Double precioMax
    ){
        //llamada al metodo del service
        List<Producto> productosFiltrados = catalogoService.buscarProductos(categoria, precioMin, precioMax);

        //convertir a DTOs
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto p : productosFiltrados){
            productosDTO.add(new ProductoDTO(p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()));
        }

        //respuesta
        ApiResponse<List<ProductoDTO>> response = new ApiResponse<>(
                200,
                "Busqueda realizada con exito",
                productosDTO
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Ordenar los productos del catalogo")
    @GetMapping("/ordenar")
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> ordenarCatalogo(
        @RequestParam(required=false) String criterio,
        @RequestParam(defaultValue = "asc") String orden
    ){

        List<Producto> productosOrdenados = catalogoService.ordenarProductos(criterio, orden);
            
        //convertir a DTO
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto p : productosOrdenados) {
            productosDTO.add(new ProductoDTO(p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()));
        }

        //respuesta
        ApiResponse<List<ProductoDTO>> response = new ApiResponse<>(
                200,
                "Ordenamiento realizado con exito",
                productosDTO
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Agregar un nuevo producto al catálogo")
    @PostMapping
    public ResponseEntity<ApiResponse<ProductoDTO>> agregarProducto(
            //validaciones
            @Valid @RequestBody ProductoDTO productoIngresado
    ) {
        
        //enviar a service
        Producto productoCreado = catalogoService.agregarProducto(productoIngresado);

        //model a dto
        ProductoDTO responseDTO = new ProductoDTO(
                productoCreado.getId(),
                productoCreado.getNombre(),
                productoCreado.getCategoria(),
                productoCreado.getPrecio(),
                productoCreado.getStock()
        );

        //response
        ApiResponse<ProductoDTO> response = new ApiResponse<>(
                201, 
                "Producto agregado al catalogo exitosamente",
                responseDTO
        );

        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Modificar el stock de un producto")
    @PutMapping("{id}/stock")
    public ResponseEntity<ApiResponse<ProductoDTO>> descontarStock(
        @PathVariable Long id,
        @RequestParam @jakarta.validation.constraints.Positive(message = "La cantidad a descontar debe ser mayor a cero") Integer cantidad
    ){
        //busqueda y resta la hace el service
        Producto productoActualizado = catalogoService.descontarStock(id, cantidad);

        //model a DTO
        ProductoDTO responseDTO = new ProductoDTO(
            productoActualizado.getId(),
            productoActualizado.getNombre(),
            productoActualizado.getCategoria(),
            productoActualizado.getPrecio(),
            productoActualizado.getStock()
        );

        //response
        ApiResponse<ProductoDTO> response = new ApiResponse<>(
                200,
                "Stock actualizado con exito",
                responseDTO
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar producto del catalogo")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarProducto(@PathVariable Long id){
        //eliminacion a cargo del service
        catalogoService.eliminarProducto(id);

        //response
        ApiResponse<Void> response = new ApiResponse<>(
                200,
                "Producto eliminado con exito",
                null
        );
        return ResponseEntity.ok(response);
    }
}