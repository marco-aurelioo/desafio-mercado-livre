package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.PerguntaModel;
import com.dev.mercadolivre.repository.PerguntaRepository;
import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerguntaService {

    private PerguntaRepository perguntaRepository;
    private ProdutoRepository produtoRepository;
    private UserRepository usuarioRepository;

    public PerguntaService(
            @Autowired PerguntaRepository perguntaRepository,
            @Autowired ProdutoRepository produtoRepository,
            @Autowired UserRepository usuarioRepository

    ){
        this.perguntaRepository = perguntaRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PerguntaModel createPergunta( PerguntaModel perguntaModel){
        return perguntaRepository.save(
                perguntaModel.toEntity( produtoRepository, usuarioRepository))
                .toModel();
    }

}
