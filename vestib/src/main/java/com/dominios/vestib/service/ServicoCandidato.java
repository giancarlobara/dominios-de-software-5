package com.dominios.vestib.service;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.repository.RepositorioCandidato;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoCandidato {
    private final RepositorioCandidato repositorioCandidato;
    private final ServicoPessoa servicoPessoa;

    public ServicoCandidato(RepositorioCandidato repositorioCandidato, ServicoPessoa servicoPessoa) {
        this.repositorioCandidato = repositorioCandidato;
        this.servicoPessoa = servicoPessoa;
    }
    public Long save(Candidato candidato){
        Optional<Candidato> optionalCandidato = repositorioCandidato.findByCodigo(candidato.getCodigo());
        optionalCandidato.ifPresent(value -> candidato.setId(value.getId()));
        repositorioCandidato.save(candidato);
        return candidato.getId();
    }

}
