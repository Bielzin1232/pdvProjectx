package com.pdv.projectX.services;

import com.pdv.projectX.dtos.CadastrarProdutoDTO;
import com.pdv.projectX.dtos.VitrineProdutosDTO;
import com.pdv.projectX.entities.Categoria;
import com.pdv.projectX.entities.Produto;
import com.pdv.projectX.repository.CategoriaRepository;
import com.pdv.projectX.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional(readOnly = true)
    public List<VitrineProdutosDTO> listarProdutos() {

        return  produtoRepository.findAll().stream().map(produto-> new VitrineProdutosDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPrecoProduto(),
                produto.getCategoria().getNome(),
                produto.getCaminhoImagem(),
                produto.getEstoque()
        )).collect(Collectors.toList());
    }

    //Regra de negócio adicionar produto ao banco de dados!
    @Transactional
    public Produto adicionarProduto(CadastrarProdutoDTO cadastrarProdutoDTO) {
       Produto p = new Produto();
       p.setNome(cadastrarProdutoDTO.nome());
       p.setDescricao(cadastrarProdutoDTO.descricao());
       p.setPrecoProduto(cadastrarProdutoDTO.precoProduto());
       p.setEstoque(cadastrarProdutoDTO.estoque());
        Categoria cat = categoriaRepository.findById(cadastrarProdutoDTO.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria do produto não encontrada!"));
        p.setCategoria(cat);
       Produto produtoSalvo =  produtoRepository.save(p);
       return produtoSalvo;
    }






}
