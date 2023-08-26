package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.AuthenticationException;
import java.util.logging.Logger;

@RestController
@Validated
public class ImagemController {

    Logger logg = Logger.getLogger(ImagemController.class.getName());

    private ProdutoService produtoService;

    public ImagemController(@Autowired ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto/{id}/add-images")
    public ResponseEntity<Boolean> upload(@RequestBody MultipartFile[] file,
                                 @PathVariable("id") Integer produtoId,
                                 @AuthenticationPrincipal UserDetails user) throws AuthenticationException {
        produtoService.upload(file, user, produtoId);
        return ResponseEntity.ok(true);
    }

}
