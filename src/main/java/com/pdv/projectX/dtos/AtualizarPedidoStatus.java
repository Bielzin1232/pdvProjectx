package com.pdv.projectX.dtos;

import com.pdv.projectX.enums.PedidoStatus;
import jakarta.validation.constraints.NotNull;

public record AtualizarPedidoStatus(
        @NotNull(message = "O status é obrigatório")
        PedidoStatus status

) {
}
