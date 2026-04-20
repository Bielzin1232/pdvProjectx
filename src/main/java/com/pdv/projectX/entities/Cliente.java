package com.pdv.projectX.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)



    //Evitar erros de seguraça de algum hacker explodir o banco de dados com Strings gigantesticas
    @Size(min = 1, max = 50)
    @NotNull(message = "O nome do cliente não pode estar vazio!")
    String nome;

    @Size(min = 11, max = 11)
    @NotNull(message = "O número de telefone não pode ser vazio!")
    int numeroTelefone;

}
