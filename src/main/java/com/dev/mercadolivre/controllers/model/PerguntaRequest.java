package com.dev.mercadolivre.controllers.model;

import com.dev.mercadolivre.model.PerguntaModel;
import com.dev.mercadolivre.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;

public class PerguntaRequest {

    @NotEmpty
    private String titulo;
    @NotEmpty
    private String pergunta;

    public PerguntaRequest(String titulo, String pergunta) {
        this.titulo = titulo;
        this.pergunta = pergunta;
    }

    public PerguntaRequest() {

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

    public PerguntaModel toModel(Integer id, UserDetails user) {
        return new PerguntaModel(
                null,
                this.titulo,
                this.pergunta,
                id,
                new UserModel(user.getUsername()));
    }
}
