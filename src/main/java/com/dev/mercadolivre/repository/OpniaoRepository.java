package com.dev.mercadolivre.repository;

import com.dev.mercadolivre.repository.entity.OpniaoEntity;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OpniaoRepository extends CrudRepository<OpniaoEntity, Integer> {

    List<OpniaoEntity> findByProduto(ProdutoEntity produto);

    @Query("select avg(o.nota) from OpniaoEntity o where o.produto.id = :id")
    Double mediaOpinioes(Integer id);
}
