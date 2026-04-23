package com.pdv.projectX.entities;


import com.pdv.projectX.enums.PedidoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@Table(name = "pedidos")
@NoArgsConstructor
@Data
public class Pedido {

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "total_pedido", nullable = false,precision = 10,scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @NotEmpty(message = "Você precisa adicionar pelo menos um item para concluir o pedido")
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();

    @Column(name = "pedido_status",nullable = false)
    private PedidoStatus status;


}
