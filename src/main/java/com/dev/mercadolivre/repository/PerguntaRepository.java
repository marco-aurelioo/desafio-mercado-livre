package com.dev.mercadolivre.repository;

import com.dev.mercadolivre.repository.entity.PerguntaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends CrudRepository<PerguntaEntity, Integer> {

}
