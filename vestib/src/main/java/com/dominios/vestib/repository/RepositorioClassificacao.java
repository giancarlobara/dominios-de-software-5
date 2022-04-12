package com.dominios.vestib.repository;
import com.dominios.vestib.model.Classificacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioClassificacao extends CrudRepository<Classificacao, Long> {

    List<Classificacao> findByCursoIdOrderByPosicao(long idCurso);
    
    Optional<Classificacao> findByCandidatoId(Long idCandidato);
}
