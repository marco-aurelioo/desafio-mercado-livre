package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.controllers.model.OpniaoRequest;
import com.dev.mercadolivre.model.OpniaoModel;
import com.dev.mercadolivre.service.OpniaoService;
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
public class OpniaoController {

    private OpniaoService opniaoService;

    public OpniaoController(@Autowired OpniaoService opniaoService) {
        this.opniaoService = opniaoService;
    }

    @PostMapping("/produto/{id}/opnioes")
    public ResponseEntity<OpniaoModel> opniao(
            @PathVariable("id") Integer id,
            @RequestBody @Valid OpniaoRequest request,
            @AuthenticationPrincipal UserDetails user ){

        return ResponseEntity.ok( opniaoService.createOpniao(request.toModel(user.getUsername(),id) ));

    }

}
