package com.dev.mercadolivre.model;

import com.dev.mercadolivre.repository.OpniaoRepository;
import com.dev.mercadolivre.repository.PerguntaRepository;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDetalhesModel {

    private String nomeProduto;
    private Double preco;
    private List<GrupoCaracteristicaModel> caracteristicas;
    private List<String> imagens;
    private String descricao;
    private List<OpniaoModel> opinioes;
    private Integer totalOpinioes;
    private Double mediaOpinioes;
    private List<PerguntaModel> perguntas;

    public ProdutoDetalhesModel(ProdutoEntity produtoEntity, OpniaoRepository opniaoRepository, PerguntaRepository perguntaRepository) {
        this.nomeProduto = produtoEntity.getNome();
        this.descricao = produtoEntity.getDescricao();
        this.imagens = produtoEntity.getImages();
        this.caracteristicas = produtoEntity.getCaracteristicas().stream()
                .map(entity -> entity.toModel())
                .collect(Collectors.toList());
        this.opinioes = opniaoRepository.findByProduto(
                produtoEntity).stream().map(entity -> entity.toModel()).collect(Collectors.toList());
        this.totalOpinioes = this.opinioes.size();
        this.mediaOpinioes = opniaoRepository.mediaOpinioes(produtoEntity.getId());
        this.perguntas = perguntaRepository.findByProduto(produtoEntity).stream().map(entity -> entity.toModel()).collect(Collectors.toList());

    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<GrupoCaracteristicaModel> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<GrupoCaracteristicaModel> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<OpniaoModel> getOpinioes() {
        return opinioes;
    }

    public void setOpinioes(List<OpniaoModel> opinioes) {
        this.opinioes = opinioes;
    }

    public Integer getTotalOpinioes() {
        return totalOpinioes;
    }

    public void setTotalOpinioes(Integer totalOpinioes) {
        this.totalOpinioes = totalOpinioes;
    }

    public Double getMediaOpinioes() {
        return mediaOpinioes;
    }

    public void setMediaOpinioes(Double mediaOpinioes) {
        this.mediaOpinioes = mediaOpinioes;
    }

    public List<PerguntaModel> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntaModel> perguntas) {
        this.perguntas = perguntas;
    }
}
