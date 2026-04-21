package com.pdv.projectX.controllers;

import com.pdv.projectX.dtos.CriarPedidoDTO;
import com.pdv.projectX.entities.Pedido;
import com.pdv.projectX.services.PedidoService;
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


    @PostMapping("criar")
    private Pedido criarPedido(CriarPedidoDTO dto) {
        return pedidoService.criarPedido(dto);
    }

}
