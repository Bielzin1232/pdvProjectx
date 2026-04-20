package com.pdv.projectX.controllers;


import com.pdv.projectX.dtos.CadastrarProdutoDTO;
import com.pdv.projectX.entities.Produto;
import com.pdv.projectX.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/produtos")
@RestController
public class ProdutoController {



    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody CadastrarProdutoDTO cadastrarProdutoDTO) {
      return produtoService.adicionarProduto(cadastrarProdutoDTO);
    }


}