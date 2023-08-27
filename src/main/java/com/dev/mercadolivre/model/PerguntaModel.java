package com.dev.mercadolivre.model;

import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.PerguntaEntity;

import java.time.LocalDateTime;

public class PerguntaModel {

    private Integer id;
    private String titulo;
    private String  pergunta;
    private int produtoId;
    private UserModel user;

    private LocalDateTime instante;

    public PerguntaModel(Integer id, String titulo, String pergunta, int produtoId, UserModel user) {
        this.id = id;
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.produtoId = produtoId;
        this.user = user;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PerguntaEntity toEntity(ProdutoRepository produtoRepository, UserRepository userRepository) {
        return new PerguntaEntity(
                this.id,
                this.titulo,
                this.pergunta,
                produtoRepository.findById(this.produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado")),
                userRepository.findByUsername(this.user.getUsername())
        );
    }
}
