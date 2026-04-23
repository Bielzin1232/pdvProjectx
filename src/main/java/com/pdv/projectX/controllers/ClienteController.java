package com.pdv.projectX.controllers;

import com.pdv.projectX.dtos.CadastrarClienteDTO;
import com.pdv.projectX.entities.Cliente;
import com.pdv.projectX.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

   private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> cadastrarCliente(CadastrarClienteDTO dto) {
       Cliente c =  clienteService.cadastrarCliente(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(c);
   }





}
