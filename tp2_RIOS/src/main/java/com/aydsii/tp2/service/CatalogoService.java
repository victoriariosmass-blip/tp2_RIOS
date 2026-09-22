package com.aydsii.tp2.service;

import com.aydsii.tp2.dto.ProductoDTO;
import com.aydsii.tp2.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogoService {
    private List<Producto> productos = new ArrayList<>();

    //carga productos ejemplo
    public CatalogoService() {
        productos.add(new Producto(1L, "Mouse inalambrico", "Perifericos", 4500.0, 50));
        productos.add(new Producto(2L, "Teclado mecanico", "Perifericos", 25000.0, 30));
        productos.add(new Producto(3L, "Monitor 24 pulgadas", "Monitores", 150000.0, 15));
        productos.add(new Producto(4L, "Monitor 27 pulgadas", "Monitores", 180000.0, 10));
        productos.add(new Producto(5L, "Auriculares gaming", "Audio", 35000.0, 40));
        productos.add(new Producto(6L, "Microfono USB", "Audio", 45000.0, 20));
        productos.add(new Producto(7L, "Gabinete ATX", "Componentes", 60000.0, 12));
        productos.add(new Producto(8L, "Fuente 650W", "Componentes", 55000.0, 25));
    }

    //metodo para GET /api/catalogo
    public List<Producto> obtenerTodos() {
        return productos;
    }

    //metodo para GET /api/catalogo/buscar?
    public List<Producto> buscarProductos(String categoria, Double precioMin, Double precioMax){
        return productos.stream() 
            .filter(p -> categoria == null || p.getCategoria().equalsIgnoreCase(categoria))
            .filter(p -> precioMin == null || p.getPrecio() >= precioMin)
            .filter(p -> precioMax == null || p.getPrecio() <= precioMax)
            //filtrado a lista
            .toList();
    }

    //metodo para GET /api/catalogo/ordenar
    public List<Producto> ordenarProductos(String criterio, String orden){
        java.util.Comparator<Producto> comparador = java.util.Comparator.comparing(Producto::getId);

        if ("precio".equalsIgnoreCase(criterio)) {
            comparador = java.util.Comparator.comparing(Producto::getPrecio);
        } else if ("nombre".equalsIgnoreCase(criterio)) {
            comparador = java.util.Comparator.comparing(Producto::getNombre);
        }

        if ("desc".equalsIgnoreCase(orden)) {
            comparador = comparador.reversed(); //invertir orden del comparador
        }

        return productos.stream()
                .sorted(comparador)
                .toList();
    }

    //metodo para POST /api/catalogo
    public Producto agregarProducto(ProductoDTO dto) {
        
        //se busca el ID más alto que haya en la lista y sumamos 1
        long nuevoId = productos.stream()
                .mapToLong(Producto::getId)
                .max()
                .orElse(0L) + 1;

        //convertir dto en model
        Producto nuevoProducto = new Producto(
                nuevoId, 
                dto.getNombre(), 
                dto.getCategoria(), 
                dto.getPrecio(), 
                dto.getStock()
        );
        //guardar en lista
        productos.add(nuevoProducto);
        //respuesta
        return nuevoProducto;
    }

    //metodo para PUT /api/catalogo/{id}/stock?cantidad
    public Producto descontarStock(Long id, Integer cantidad){
        //buscar producto o lanzar excepcion
        Producto producto = productos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(()-> new com.aydsii.tp2.exception.ProductoNotFoundException("No se encontró el producto con ID: " + id));
        
        //validar cantidad de stock
        if(producto.getStock() < cantidad){
            throw new IllegalArgumentException("Stock insuficiente. Stock actual: " + producto.getStock());
        }

        //modificar cantidad
        producto.setStock(producto.getStock() - cantidad);
        //respuesta
        return producto;
    }

    //metodo para DELETE /api/catalogo/{id}
    public void eliminarProducto(Long id){
        Producto producto = productos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(()-> new com.aydsii.tp2.exception.ProductoNotFoundException("No se encontró el producto con ID: " + id));
    
        //eliminar
        productos.remove(producto);
    }
}