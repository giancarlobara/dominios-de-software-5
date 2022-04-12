package com.dominios.vestib.repository;
import com.dominios.vestib.model.Classificacao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RepositorioClassificacao extends CrudRepository<Classificacao, Long> {

    List<Classificacao> findByCursoIdOrderByPosicao(long idCurso);
    
    Optional<Classificacao> findByCandidatoId(Long idCandidato);

    @Transactional
    @Modifying
    void deleteAllByCursoId(long id);
}
