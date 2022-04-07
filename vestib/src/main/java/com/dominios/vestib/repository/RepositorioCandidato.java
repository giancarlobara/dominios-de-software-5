package com.dominios.vestib.repository;

import com.dominios.vestib.model.Candidato;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioCandidato extends CrudRepository<Candidato, Long> {
    Optional<Candidato> findByCodigoAndCursoId(String codigo,Long idCurso);

    List<Candidato> findByCursoId(long idCurso);
}
