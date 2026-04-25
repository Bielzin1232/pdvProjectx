package com.pdv.projectX.dtos;

import com.pdv.projectX.entities.Cliente;
import com.pdv.projectX.entities.ItemPedido;
import com.pdv.projectX.entities.Pedido;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CriarPedidoDTO(
        @NotNull Long clienteId,
        @NotNull List<ItemPedidoDTO> itens
        ) {
}
