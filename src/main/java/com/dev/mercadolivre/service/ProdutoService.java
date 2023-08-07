package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.ProdutoModel;
import com.dev.mercadolivre.model.UserModel;
import com.dev.mercadolivre.repository.CategoryRepository;
import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProdutoService {

    Logger logger = Logger.getLogger(ProdutoService.class.getName());

    private  ProdutoRepository produtoRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    ProdutoService(@Autowired ProdutoRepository produtoRepository,
                   @Autowired CategoryRepository categoryRepository,
                   @Autowired UserRepository userRepository) {
        this.produtoRepository = produtoRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    public ProdutoModel createProduto( ProdutoModel produto) {

        var produtoEntity = produtoRepository.save(produto.toEntity( this.userRepository, this.categoryRepository));
        return produtoEntity.toModel(categoryRepository);
    }

}
