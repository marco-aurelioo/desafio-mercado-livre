package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.EmailEventModel;
import com.dev.mercadolivre.model.PerguntaModel;
import com.dev.mercadolivre.repository.PerguntaRepository;
import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PerguntasService {


    private PerguntaRepository perguntaRepository;
    private ProdutoRepository produtoRepository;
    private UserRepository usuarioRepository;

    private ApplicationEventPublisher applicationEventPublisher;

    public PerguntasService(
            @Autowired PerguntaRepository perguntaRepository,
            @Autowired ProdutoRepository produtoRepository,
            @Autowired UserRepository usuarioRepository,
            @Autowired ApplicationEventPublisher applicationEventPublisher
    ){
        this.perguntaRepository = perguntaRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public PerguntaModel createPergunta(PerguntaModel perguntaModel){
        PerguntaModel pergunta = perguntaRepository.save(
                        perguntaModel.toEntity( produtoRepository, usuarioRepository))
                .toModel();
        sendEmail(pergunta);
        return pergunta;
    }

    private void sendEmail(PerguntaModel pergunta) {
        EmailEventModel emailEventModel = new EmailEventModel(
                this,
                pergunta.getTitulo(),
                pergunta.getUser().getEmail()
        );
        applicationEventPublisher.publishEvent(emailEventModel);
    }


}
