package com.pdv.projectX.controllers;

import com.pdv.projectX.dtos.CriarPedidoDTO;
import com.pdv.projectX.entities.Pedido;
import com.pdv.projectX.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/pedidos")
@RestController
public class PedidoController {

     private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    //Nem sei porque eu coloquei private
    //Criar e salvar no banco de dados
    @PostMapping("criar")
    public ResponseEntity<Pedido> criarPedido(CriarPedidoDTO dto) {
        Pedido p = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);

    }

}
