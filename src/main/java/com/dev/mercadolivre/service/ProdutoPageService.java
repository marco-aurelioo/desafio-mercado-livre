package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.ProdutoDetalhesModel;
import com.dev.mercadolivre.model.exceptions.ModelException;
import com.dev.mercadolivre.repository.OpniaoRepository;
import com.dev.mercadolivre.repository.PerguntaRepository;
import com.dev.mercadolivre.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoPageService {

    private ProdutoRepository produtoRepository;
    private OpniaoRepository opniaoRepository;
    private PerguntaRepository perguntaRepository;

    public ProdutoPageService(ProdutoRepository produtoRepository,
                              OpniaoRepository opniaoRepository,
                              PerguntaRepository perguntaRepository) {
        this.produtoRepository = produtoRepository;
        this.opniaoRepository = opniaoRepository;
        this.perguntaRepository = perguntaRepository;
    }

    public ProdutoDetalhesModel detallhesProduto(Integer id) {
        return new ProdutoDetalhesModel(
                produtoRepository.findById(id).orElseThrow(() -> new ModelException("Produto não encontrado")),
                this.opniaoRepository,
                this.perguntaRepository);
    }

}
