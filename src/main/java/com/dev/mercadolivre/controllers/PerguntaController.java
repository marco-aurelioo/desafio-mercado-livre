package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.controllers.model.PerguntaRequest;
import com.dev.mercadolivre.model.PerguntaModel;
import com.dev.mercadolivre.service.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class PerguntaController {


    private PerguntasService perguntaService;

    public PerguntaController(@Autowired PerguntasService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @PostMapping("/produto/{id}/perguntas")
    public ResponseEntity<PerguntaModel> pergunta(@PathVariable("id") Integer id,
                                                  @RequestBody @Valid PerguntaRequest request,
                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok( perguntaService.createPergunta( request.toModel(id, user)));
    }



}
