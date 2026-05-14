package com.pdv.projectX.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarProdutoDTO(

        @NotNull(message = "O nome do produto não pode ser vazio!")
        String nome,
        @NotNull(message = "Você precisa adicionar a descrição do produto")
        String descricao,
        @NotNull(message = "O produto precisa obrigatoriamente de um preço!")
        BigDecimal precoProduto,
        @NotNull(message = "Informe o estoque do produto!")
        Integer estoque,
        @NotNull(message = "Informe a categoria do produto")
        Long categoriaId
) {

}
