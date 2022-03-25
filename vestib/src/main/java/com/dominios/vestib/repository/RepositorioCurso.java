package com.dominios.vestib.repository;

import com.dominios.vestib.model.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioCurso extends CrudRepository<Curso, Long> {

    @Query("select c from Curso c order by c.codigo")
    List<Curso> findAllOrderByCodigo();
}
