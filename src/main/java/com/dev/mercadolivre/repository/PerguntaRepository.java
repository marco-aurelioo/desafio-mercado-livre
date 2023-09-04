package com.dev.mercadolivre.repository;

import com.dev.mercadolivre.repository.entity.PerguntaEntity;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends CrudRepository<PerguntaEntity, Integer> {

    List<PerguntaEntity> findByProduto(ProdutoEntity produtoEntity);
}
