package com.aydsii.tp2.service;

import com.aydsii.tp2.dto.PedidoResponseDTO;
import com.aydsii.tp2.dto.ProductoPedidoDTO;
import com.aydsii.tp2.model.DetallePedido;
import com.aydsii.tp2.model.EstadoPedido;
import com.aydsii.tp2.model.Pedido;
import com.aydsii.tp2.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoResponseDTO> buscarPedidos(
            Long clienteId,
            String categoria,
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            EstadoPedido estado
    ) {
        //busqueda al repositorio
        List<Pedido> pedidos = pedidoRepository.buscarPedidos(
                clienteId,
                categoria,
                fechaDesde,
                fechaHasta,
                estado
        );

        //lista de rta
        List<PedidoResponseDTO> respuesta = new ArrayList<>();
        
        //entidad pedido a pedidoresponsedto
        for (Pedido pedido : pedidos) {
            PedidoResponseDTO dto = convertirAPedidoDTO(pedido);
            respuesta.add(dto);
        }
        return respuesta;
    }

    private PedidoResponseDTO convertirAPedidoDTO(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        
        dto.setPedidoId(pedido.getId());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado().toString());
        
        String nombreCompleto = pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido();
        dto.setCliente(nombreCompleto);

        List<ProductoPedidoDTO> productos = new ArrayList<>();
        double totalCalculado = 0.0; 
        
        for (DetallePedido detalle : pedido.getDetalles()) {
            ProductoPedidoDTO productoDTO = new ProductoPedidoDTO();
            
            productoDTO.setNombre(detalle.getProducto().getNombre());
            productoDTO.setCategoria(detalle.getProducto().getCategoria().getNombre());
            productoDTO.setCantidad(detalle.getCantidad());
            
            //subtotal = cantidad * precio unitario
            double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
            productoDTO.setSubtotal(subtotal);
            
            //subtotal acumula en total
            totalCalculado += subtotal;
            
            productos.add(productoDTO);
        }
        
        dto.setProductos(productos);
        dto.setTotalPedido(totalCalculado); //asignar total que calculamos
        
        return dto;
    }
}