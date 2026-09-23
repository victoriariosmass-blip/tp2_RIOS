package com.aydsii.tp2.controller;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.ClienteDTO;
import com.aydsii.tp2.model.Cliente;
import com.aydsii.tp2.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController{
    private final ClienteService clienteService;
    //inyeccion de dependencias
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cliente>> registrarClienteSimple(@RequestBody ClienteDTO clienteDTO){
        //dto al service
        Cliente clienteCreado = clienteService.registrarCliente(clienteDTO);

        //response
        ApiResponse<Cliente> response = new ApiResponse<>(
            201,
            "Cliente registrado con éxito",
            clienteCreado //se devuelve la entidad creada que va a tener su id generado por la bd
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}