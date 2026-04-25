package com.pdv.projectX.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTO(
        @NotNull(message = "O ID do produto é obrigatório")
        Long produtoId,
        @Positive(message = "A quantidade precisa ser maior que zero")
        @NotNull(message = "A quantidade precisa ser maior que zero")
        Integer quantidade

) {
}
