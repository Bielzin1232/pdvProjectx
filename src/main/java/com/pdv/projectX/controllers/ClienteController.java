package com.pdv.projectX.controllers;

import com.pdv.projectX.dtos.CadastrarClienteDTO;
import com.pdv.projectX.entities.Cliente;
import com.pdv.projectX.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

   private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody CadastrarClienteDTO dto) {
       Cliente c =  clienteService.cadastrarCliente(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(c);
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        clienteService.removerCliente(id);
        return ResponseEntity.noContent().build();
    }



}
