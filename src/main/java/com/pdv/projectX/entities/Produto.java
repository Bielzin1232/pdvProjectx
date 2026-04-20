package com.pdv.projectX.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 32)
    @NotNull(message = "O seu produto precisar ter um nome")
    private String nome;

    @Size(min = 1, max = 100)
    @NotNull(message = "Você precisa adicionar uma descrição para o seu produto")
    private String descricao;




}
