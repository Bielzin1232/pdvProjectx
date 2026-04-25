package com.pdv.projectX.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastrarClienteDTO(
        @Size(min = 1, max = 50)
        @NotBlank(message = "O nome do cliente não pode ser nulo")
        String nome,
        @Size(min = 11, max = 11)
        @Pattern(regexp = "^[0-9]{10,11}$",message = "O número de telefone pode ter apenas números!")
        @NotNull(message = "O número de telefone não pode ser nulo")
        String numeroTelefone





) {
}
