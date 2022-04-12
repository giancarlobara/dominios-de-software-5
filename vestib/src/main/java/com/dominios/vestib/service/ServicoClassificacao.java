package com.dominios.vestib.service;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Classificacao;
import com.dominios.vestib.repository.RepositorioClassificacao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoClassificacao {
    private final RepositorioClassificacao repositorioClassificacao;

    public ServicoClassificacao(RepositorioClassificacao repositorioClassificacao) {
        this.repositorioClassificacao = repositorioClassificacao;
    }
    public Long save(Classificacao classificacao){
        repositorioClassificacao.save(classificacao);
        return classificacao.getId();
    }
    public List<Classificacao> getClassificacao(long idCurso){
        return repositorioClassificacao.findByCursoIdOrderByPosicao(idCurso);
    }
    public Classificacao getClassificacaoByCandidato(long idCandidato){
        Optional<Classificacao> classificacao = repositorioClassificacao.findByCandidatoId(idCandidato);
        return (classificacao.isPresent())? classificacao.get() : null;
    }
}
