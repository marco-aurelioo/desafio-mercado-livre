package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.controllers.model.ProdutoRequest;
import com.dev.mercadolivre.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class ProdutoController {

    private ProdutoService  produtoService;

    public ProdutoController(ProdutoService  produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping("/produto")
    public ResponseEntity<Void> cadastrarProduto(@Valid @RequestBody ProdutoRequest request,
                                                 @AuthenticationPrincipal UserDetails user ){
        produtoService.createProduto(request.toModel(user));
        return ResponseEntity.ok().build();
    }

}
