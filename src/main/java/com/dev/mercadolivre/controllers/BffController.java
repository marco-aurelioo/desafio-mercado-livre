package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.model.ProdutoDetalhesModel;
import com.dev.mercadolivre.service.ProdutoPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BffController {


    private ProdutoPageService produtoPageService;

    public BffController(ProdutoPageService produtoPageService) {
        this.produtoPageService = produtoPageService;
    }

    @GetMapping("/bff/produtopage/{id}")
    public ResponseEntity<ProdutoDetalhesModel> produtoPage(@PathVariable("id") Integer id){
        return ResponseEntity.ok(produtoPageService.detallhesProduto(id));
    }

}
