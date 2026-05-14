package com.pdv.projectX.controllers;


import com.pdv.projectX.dtos.CadastrarProdutoDTO;
import com.pdv.projectX.dtos.VitrineProdutosDTO;
import com.pdv.projectX.entities.Produto;
import com.pdv.projectX.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/produtos")
@RestController
public class ProdutoController {



    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    //Listar os produtos
    @GetMapping("listar")
    public ResponseEntity<List<VitrineProdutosDTO>> listar() {
        List<VitrineProdutosDTO> lista = produtoService.listarProdutos();
        return ResponseEntity.ok(lista);
    }


    //Cadastrar um produto acionando o service!
    @PostMapping("cadastrar")
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody CadastrarProdutoDTO cadastrarProdutoDTO) {
      Produto produtoSalvo = produtoService.adicionarProduto(cadastrarProdutoDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }


}