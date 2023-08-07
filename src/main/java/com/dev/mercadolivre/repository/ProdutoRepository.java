package com.dev.mercadolivre.repository;

import com.dev.mercadolivre.repository.entity.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Integer> {

}
