package com.pdv.projectX.dtos;

import java.math.BigDecimal;

public record VitrineProdutosDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String categoriaNome,
        String caminhoImagem,
        Integer estoque
) {
}
