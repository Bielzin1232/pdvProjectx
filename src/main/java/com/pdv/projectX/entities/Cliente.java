package com.pdv.projectX.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //Evitar erros de seguraça de algum hacker explodir o banco de dados com Strings gigantesticas
    @Column(name = "cliente_nome",nullable = false)
    @Size(min = 1, max = 50)
    @NotNull(message = "O nome do cliente não pode estar vazio!")
    String nome;

    //Mudei para long!!
    @Column(name = "numero_telefone")
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^[0-9]{10,11}$",message = "O número de telefone pode ter apenas números!")
    @NotEmpty(message = "O número de telefone não pode ser vazio!")
    String numeroTelefone;

}
