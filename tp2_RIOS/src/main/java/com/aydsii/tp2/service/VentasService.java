package com.aydsii.tp2.service;

import com.techstore.dto.EstadisticasVentasDTO;
import com.techstore.dto.VentaDTO;
import com.techstore.dto.ResultadoDescuentoDTO; 
import com.techstore.dto.VentaConDescuentoDTO;  
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VentasService {
    public EstadisticasVentasDTO procesarEstadisticas(List<VentaDTO> ventas){
        //inicializacion punto de partida
        double totalFacturado = 0.0;
        VentaDTO ventaMayor = ventas.get(0);
        VentaDTO ventaMenor = ventas.get(0);

        //map para acumular unidades vendidas
        Map<String, Integer> cantidadesPorProducto = new HashMap<>();

        //recorrer lista ventas
        for (VentaDTO venta : ventas){
            double montoVenta = venta.getCantidad() * venta.getPrecioUnitario();
            totalFacturado += montoVenta;

            //verificar si es venta mayor
            if (montoVenta > (ventaMayor.getCantidad() * ventaMayor.getPrecioUnitario())) {
                ventaMayor = venta;
            }
            //verificar si es venta menor
            if (montoVenta < (ventaMenor.getCantidad() * ventaMenor.getPrecioUnitario())) {
                ventaMenor = venta;
            }

            //acumular en map
            String nombreProducto = venta.getProducto();
            int cantidadActual = cantidadesPorProducto.getOrDefault(nombreProducto, 0);
            cantidadesPorProducto.put(nombreProducto, cantidadActual + venta.getCantidad());
        }

        //buscar mas vendido en map
        String productoMasVendido = "";
        int maxCantidad = 0;
        for (Map.Entry<String, Integer> entry : cantidadesPorProducto.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                maxCantidad = entry.getValue();
                productoMasVendido = entry.getKey(); // El nombre del producto
            }
        }

        //armar DTO con valores calculados
        EstadisticasVentasDTO estadisticas = new EstadisticasVentasDTO();
        estadisticas.setTotalFacturado(totalFacturado);
        estadisticas.setCantidadVentas(ventas.size());
        estadisticas.setTicketPromedio(totalFacturado / ventas.size());
        estadisticas.setVentaMayor(ventaMayor);
        estadisticas.setVentaMenor(ventaMenor);
        estadisticas.setProductoMasVendido(productoMasVendido);

        return estadisticas;
    }

    //descuento
    public ResultadoDescuentoDTO aplicarDescuento(List<VentaDTO> ventas, Double porcentaje) {
        ResultadoDescuentoDTO resultado = new ResultadoDescuentoDTO();
        
        //lista para resultdaos
        java.util.List<VentaConDescuentoDTO> ventasConDescuento = new java.util.ArrayList<>();
        double total = 0.0;

        for (VentaDTO venta : ventas) {
            VentaConDescuentoDTO vcd = new VentaConDescuentoDTO();
            vcd.setProducto(venta.getProducto());
            vcd.setCantidad(venta.getCantidad());
            vcd.setPrecioUnitario(venta.getPrecioUnitario());

            //monto original + descuento
            double montoOriginal = venta.getCantidad() * venta.getPrecioUnitario();
            double descuentoAplicado = montoOriginal * (porcentaje / 100.0);
            double montoFinal = montoOriginal - descuentoAplicado;
            
            vcd.setMontoConDescuento(montoFinal);
            
            ventasConDescuento.add(vcd);
            total += montoFinal;
        }

        resultado.setVentas(ventasConDescuento);
        resultado.setTotalConDescuento(total);
        
        return resultado;
    }
}