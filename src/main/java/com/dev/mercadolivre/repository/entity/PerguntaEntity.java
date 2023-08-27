package com.dev.mercadolivre.repository.entity;

import com.dev.mercadolivre.model.PerguntaModel;
import com.dev.mercadolivre.model.UserModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "perguntas")
public class PerguntaEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String titulo;
    @NotEmpty
    private String pergunta;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;
    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private UserEntity userOwner;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public PerguntaEntity() { }

    public PerguntaEntity(Integer id, String titulo, String pergunta, ProdutoEntity produto, UserEntity userOwner) {
        this.id = id;
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.produto = produto;
        this.userOwner = userOwner;
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

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public UserEntity getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserEntity userOwner) {
        this.userOwner = userOwner;
    }

    public PerguntaModel toModel() {
        return new PerguntaModel(
                this.id,
                this.titulo,
                this.pergunta,
                this.produto.getId(),
                new UserModel(this.userOwner.getUsername()) );
    }

    @PreUpdate
    private void postUpdateFunction(){
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    private void prePersistFunction(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
