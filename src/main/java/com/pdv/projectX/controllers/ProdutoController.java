package com.pdv.projectX.controllers;


import com.pdv.projectX.dtos.CadastrarProdutoDTO;
import com.pdv.projectX.entities.Produto;
import com.pdv.projectX.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/produtos")
@RestController
public class ProdutoController {



    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    //Cadastrar um produto acionando o service!
    @PostMapping("cadastrar")
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody CadastrarProdutoDTO cadastrarProdutoDTO) {
      Produto produtoSalvo = produtoService.adicionarProduto(cadastrarProdutoDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }


}