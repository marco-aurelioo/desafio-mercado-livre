package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.OpniaoModel;
import com.dev.mercadolivre.model.exceptions.ModelException;
import com.dev.mercadolivre.repository.OpniaoRepository;
import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpniaoService {

    private OpniaoRepository opniaoRepository;

    private UserRepository userRepository;

    private ProdutoRepository produtoRepository;

    public OpniaoService( @Autowired OpniaoRepository opniaoRepository,
                          @Autowired ProdutoRepository produtoRepository,
                          @Autowired UserRepository userRepository){
        this.opniaoRepository = opniaoRepository;
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
    }


    public OpniaoModel createOpniao(OpniaoModel opniaoModel) {
        ProdutoEntity produto = produtoRepository.findById(opniaoModel.getProdutoId()).orElseThrow(() -> new ModelException("Produto não encontrado"));
        return opniaoRepository.save(opniaoModel.toEntity(userRepository, produto)).toModel();
    }

}
