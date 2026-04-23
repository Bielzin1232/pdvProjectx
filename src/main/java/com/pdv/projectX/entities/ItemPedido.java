package com.pdv.projectX.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id",nullable = false)
    private Produto produto;

    @Column(name = "quantidade_item",nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario",nullable = false,precision = 10,scale = 2)
    private BigDecimal valorUnitario;

    public BigDecimal getSubTotal() {
        if(valorUnitario == null || quantidade == null) {
            return BigDecimal.ZERO;
        }
        return valorUnitario.multiply(new BigDecimal(quantidade));

    }


}
