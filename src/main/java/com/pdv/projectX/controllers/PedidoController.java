package com.pdv.projectX.controllers;

import com.pdv.projectX.dtos.AtualizarPedidoStatus;
import com.pdv.projectX.dtos.CriarPedidoDTO;
import com.pdv.projectX.entities.Pedido;
import com.pdv.projectX.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/pedidos")
@RestController
public class PedidoController {

     private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }




    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id,@Valid @RequestBody AtualizarPedidoStatus dto) {
        Pedido p = pedidoService.atualizarStatus(id, dto);
        return ResponseEntity.ok(p);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido p = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(p);
    }


    //Nem sei porque eu coloquei private
    //Criar e salvar no banco de dados
    @PostMapping("criar")
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody CriarPedidoDTO dto) {
        Pedido p = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);

    }

}
