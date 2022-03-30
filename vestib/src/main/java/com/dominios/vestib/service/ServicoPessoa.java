package com.dominios.vestib.service;

import com.dominios.vestib.model.Pessoa;
import com.dominios.vestib.repository.RepositorioPessoa;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoPessoa {
    private final RepositorioPessoa repositorioPessoa;

    public ServicoPessoa(RepositorioPessoa repositorioPessoa) {
        this.repositorioPessoa = repositorioPessoa;
    }
    public Long save(Pessoa pessoa){
        Optional<Pessoa> optionalPessoa = repositorioPessoa.findByCpf(pessoa.getCpf());
        optionalPessoa.ifPresent(value -> pessoa.setId(value.getId()));
        repositorioPessoa.save(pessoa);
        return pessoa.getId();
    }
}
