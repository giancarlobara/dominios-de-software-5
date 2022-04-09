package com.dominios.vestib.repository;

import com.dominios.vestib.model.Disciplina;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RepositorioDisciplina extends CrudRepository<Disciplina,Long> {
    @Query("select d from Disciplina d where d.curso.id = :idCurso")
    List<Disciplina> findByIdCurso(Long idCurso);

    @Query("select d from Disciplina d where d.nome = :nome and d.curso.id = :idCurso")
    Optional<Disciplina> findByNomeAndCursoId(String nome,Long idCurso);
    @Override
    List<Disciplina> findAll();
    @Transactional
    @Modifying
    void deleteAllByCursoId(long id);
}
