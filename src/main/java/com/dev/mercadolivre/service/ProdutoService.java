package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProdutoService {

    Logger logger = Logger.getLogger(ProdutoService.class.getName());

    public void createProduto(UserModel user, String produto) {
        logger.info("Criando produto " + produto + " para o usuário " + user.getUsername());
    }

}
