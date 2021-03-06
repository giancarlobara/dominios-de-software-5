package com.dominios.vestib.repository;

import com.dominios.vestib.model.Candidato;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RepositorioCandidato extends CrudRepository<Candidato, Long> {
    Optional<Candidato> findByCodigoAndCursoId(String codigo,Long idCurso);
    Optional<Candidato> findByCodigo(String codigo);
    List<Candidato> findByCursoId(long idCurso);

    List<Candidato> findByCursoIdAndNomeImagemIsNull(long idCurso);

    @Transactional
    @Modifying
    void deleteAllByCursoId(long id);
}
