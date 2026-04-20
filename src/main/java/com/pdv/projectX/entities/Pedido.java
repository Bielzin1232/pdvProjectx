package com.pdv.projectX.entities;


import com.pdv.projectX.enums.PedidoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private LocalDateTime dataPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_nome")
    private Cliente cliente;

    private BigDecimal total;

    @NotNull(message = "Você precisa adicionar pelo menos um item para concluir o pedido")
    private ArrayList<ItemPedido> itensPedido;

    private PedidoStatus status;


}
