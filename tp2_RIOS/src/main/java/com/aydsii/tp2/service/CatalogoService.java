package com.aydsii.tp2.service;

import com.aydsii.tp2.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogoService {
    //lista de productos 
    private List<Productos> productos = new ArrayList<>();

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

    //metodo para el get /api/catalogo
    public List<Producto> obtenerTodos() {
        return productos;
    }
}