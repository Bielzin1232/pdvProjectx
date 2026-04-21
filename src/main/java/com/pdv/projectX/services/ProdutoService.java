package com.pdv.projectX.services;

import com.pdv.projectX.dtos.CadastrarProdutoDTO;
import com.pdv.projectX.entities.Produto;
import com.pdv.projectX.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    //Regra de negócio adicionar produto ao banco de dados!
    @Transactional
    public Produto adicionarProduto(CadastrarProdutoDTO cadastrarProdutoDTO) {
       Produto p = new Produto();
       p.setNome(cadastrarProdutoDTO.nome());
       p.setDescricao(cadastrarProdutoDTO.descricao());
       p.setPrecoProduto(cadastrarProdutoDTO.precoProduto());
       Produto produtoSalvo =  produtoRepository.save(p);
       return produtoSalvo;
    }






}
