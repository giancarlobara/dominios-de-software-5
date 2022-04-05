package com.dominios.vestib.service;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.repository.RepositorioCandidato;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class ServicoCandidato {
    private final RepositorioCandidato repositorioCandidato;
    private final ServicoPessoa servicoPessoa;

    public ServicoCandidato(RepositorioCandidato repositorioCandidato, ServicoPessoa servicoPessoa) {
        this.repositorioCandidato = repositorioCandidato;
        this.servicoPessoa = servicoPessoa;
    }
    public Long save(Candidato candidato){
        Optional<Candidato> optionalCandidato = repositorioCandidato.findByCodigoAndCursoId(candidato.getCodigo(),candidato.getCurso().getId());
        optionalCandidato.ifPresent(value -> candidato.setId(value.getId()));
        repositorioCandidato.save(candidato);
        return candidato.getId();
    }
    public Optional<Candidato> getCandidatoByCodigo(String codigo,Long idCurso){
        return repositorioCandidato.findByCodigoAndCursoId(codigo,idCurso);
    }
    public String formatGabarito(Collection<Map.Entry<String, String>> entries){
        String gabarito = "";
        Map<Integer, String> questoes = new TreeMap<>();
        for (Map.Entry<String, String> entry : entries) {
            int key = Integer.parseInt(entry.getKey().replaceAll("[\\D]", ""));
            String value = entry.getValue();
            questoes.put(key, value);
        }
        Collection<String> values = questoes.values();
        for (String v : values) {
            gabarito = gabarito.concat(v);
        }
        gabarito = gabarito.replaceAll("_", "");
        System.out.println(gabarito);
        return gabarito;
    }
}
