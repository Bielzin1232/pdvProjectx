package com.pdv.projectX.services;


import com.pdv.projectX.dtos.CriarPedidoDTO;
import com.pdv.projectX.dtos.ItemPedidoDTO;
import com.pdv.projectX.entities.Cliente;
import com.pdv.projectX.entities.ItemPedido;
import com.pdv.projectX.entities.Pedido;
import com.pdv.projectX.entities.Produto;
import com.pdv.projectX.enums.PedidoStatus;
import com.pdv.projectX.repository.ClienteRepository;
import com.pdv.projectX.repository.PedidoRepository;
import com.pdv.projectX.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PedidoService {



    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;


    public PedidoService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

   //Método criar pedido e vincular ao cliente
    @Transactional
    public Pedido criarPedido(CriarPedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteID()).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
      Pedido novoPedido = new Pedido();
      novoPedido.setDataPedido(LocalDateTime.now());
      novoPedido.setCliente(cliente);
      novoPedido.setStatus(PedidoStatus.AGUARDANDO_PAGAMENTO);
      novoPedido.setTotal(BigDecimal.ZERO);
      for(ItemPedidoDTO item : dto.itens()) {

          //Produto vindo do banco de dados e sendo chamado aqui! pelo ID do item
          Produto p = produtoRepository.findById(item.produtoID()).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

          ItemPedido novoItem =  new ItemPedido();
          novoItem.setProduto(p);
          novoItem.setQuantidade(item.quantidade());
          novoItem.setValorUnitario(p.getPrecoProduto());
          novoItem.setPedido(novoPedido);

          BigDecimal subTotal = novoItem.getValorUnitario().multiply(new BigDecimal(novoItem.getQuantidade()));
          novoPedido.setTotal(novoPedido.getTotal().add(subTotal));
      }
      return pedidoRepository.save(novoPedido);
    }



}