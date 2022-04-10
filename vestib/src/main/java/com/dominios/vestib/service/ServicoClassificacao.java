package com.dominios.vestib.service;

import com.dominios.vestib.model.Classificacao;
import com.dominios.vestib.repository.RepositorioClassificacao;
import org.springframework.stereotype.Service;

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
}
