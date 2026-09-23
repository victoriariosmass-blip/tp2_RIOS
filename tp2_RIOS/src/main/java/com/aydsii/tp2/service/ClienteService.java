package com.aydsii.tp2.service;

import com.aydsii.tp2.dto.ClienteDTO;
import com.aydsii.tp2.model.Cliente;
import com.aydsii.tp2.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    //inyeccion de dependencias
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(ClienteDTO clienteDTO) {
        
        //verificar si email ya existe
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya esta registrado");
        }

        //dto a entidad bd
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(clienteDTO.getNombre());
        nuevoCliente.setApellido(clienteDTO.getApellido());
        nuevoCliente.setEmail(clienteDTO.getEmail());
        nuevoCliente.setTelefono(clienteDTO.getTelefono());
        
        //completar datos automaticos de sistema (fecha)
        nuevoCliente.setFechaRegistro(LocalDateTime.now());

        //guardar en bd y devolver entidad con el id generado
        return clienteRepository.save(nuevoCliente);
    }
}