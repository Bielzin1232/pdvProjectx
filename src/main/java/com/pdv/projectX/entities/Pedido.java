package com.pdv.projectX.entities;


import com.pdv.projectX.enums.FormaPagamento;
import com.pdv.projectX.enums.PedidoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "pedidos")
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    @Column(name = "pedido_formapagamento",nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "total_pedido", nullable = false,precision = 10,scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @NotEmpty(message = "Você precisa adicionar pelo menos um item para concluir o pedido")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemPedido> itensPedido = new ArrayList<>();

    @Column(name = "pedido_status",nullable = false)
    private PedidoStatus status;


}
