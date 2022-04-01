package com.dominios.vestib.repository;

import com.dominios.vestib.model.Disciplina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import java.util.List;

public interface RepositorioDisciplina extends CrudRepository<Disciplina,Long> {
    @Query("select d from Disciplina d where d.curso.id = :idCurso")
    List<Disciplina> findByIdCurso(Long idCurso);
}
