package com.pdv.projectX.dtos;

import com.pdv.projectX.enums.FormaPagamento;

public record AtualizarFormaPagamentoDTO(

        Long pedidoID,
        FormaPagamento formaPagamento



) {
}
