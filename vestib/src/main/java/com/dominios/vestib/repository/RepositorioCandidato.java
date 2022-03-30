package com.dominios.vestib.repository;

import com.dominios.vestib.model.Candidato;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RepositorioCandidato extends CrudRepository<Candidato, Long> {
    Optional<Candidato> findByCodigo(String codigo);
}
