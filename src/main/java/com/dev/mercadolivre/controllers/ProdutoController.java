package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.controllers.model.ProdutoRequest;
import com.dev.mercadolivre.model.UserModel;
import com.dev.mercadolivre.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
//@Validated
public class ProdutoController {

    private ProdutoService  produtoService;

    public ProdutoController(ProdutoService  produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarProduto(@RequestBody ProdutoRequest request,
                                                 @AuthenticationPrincipal UserDetails user ){
        produtoService.createProduto(null,request.getNome());
        return ResponseEntity.ok().build();
    }

}
