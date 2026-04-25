package com.pdv.projectX.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 32)
    @NotBlank(message = "O seu produto precisar ter um nome")
    @Column(name = "nome",nullable = false, length = 32)
    private String nome;


    @NotNull(message = "O estoque do produto é obrigatório!")
    @Column(name = "estoque",nullable = false)
    private Integer estoque;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


    @Size(min = 1, max = 100)
    @NotBlank(message = "Você precisa adicionar uma descrição para o seu produto")
    @Column(name = "descricao",length = 100,nullable = false)
    private String descricao;

    @NotNull(message = "O produto precisa ter um preço!")
    @Column(name = "preco_produto",nullable = false,precision = 10,scale = 2)
    private BigDecimal precoProduto = BigDecimal.ZERO;




}
