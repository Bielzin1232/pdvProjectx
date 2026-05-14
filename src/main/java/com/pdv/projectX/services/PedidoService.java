package com.pdv.projectX.services;


import com.pdv.projectX.dtos.AtualizarPedidoStatus;
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
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PedidoService {



    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final MessageSource messageSource;
    public PedidoService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository, MessageSource messageSource) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.messageSource = messageSource;
    }






    public Pedido buscarPorId(Long id){
      return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum pedido encontrado com esse id: "+id));
    }



    @Transactional
    public Pedido atualizarStatus(Long id, AtualizarPedidoStatus dto) {
        Pedido p = buscarPorId(id);
        p.setStatus(dto.status());
        return pedidoRepository.save(p);
    }

    @Transactional
    public Pedido criarPedido(CriarPedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
      Pedido novoPedido = new Pedido();
      novoPedido.setDataPedido(LocalDateTime.now());
      novoPedido.setCliente(cliente);
      novoPedido.setStatus(PedidoStatus.AGUARDANDO_PAGAMENTO);
      novoPedido.setTotal(BigDecimal.ZERO);
      for(ItemPedidoDTO item : dto.itens()) {

          Produto p = produtoRepository.findById(item.produtoId()).orElseThrow(() -> {
              String mensagem = messageSource.getMessage("produto.nao.encontrado", null, LocaleContextHolder.getLocale());
              return new RuntimeException(mensagem);
          });
          if(dto.itens().size() <= p.getEstoque()) {
          ItemPedido novoItem =  new ItemPedido();
          novoItem.setProduto(p);
          novoItem.setQuantidade(item.quantidade());
          novoItem.setValorUnitario(p.getPrecoProduto());
          novoItem.setPedido(novoPedido);
          novoPedido.getItensPedido().add(novoItem);
          BigDecimal subTotal = novoItem.getValorUnitario().multiply(new BigDecimal(novoItem.getQuantidade()));
          novoPedido.setTotal(novoPedido.getTotal().add(subTotal));
      }
      }

      return pedidoRepository.save(novoPedido);
    }


}