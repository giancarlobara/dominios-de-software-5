package com.dominios.vestib.controller;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Pessoa;
import com.dominios.vestib.repository.RepositorioCandidato;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.PersistenceException;
@RequestMapping("candidatos")
@Controller
public class ControleCandidato {
    private final RepositorioCandidato repositorioCandidato;

    public ControleCandidato(RepositorioCandidato repositorioCandidato) {
        this.repositorioCandidato = repositorioCandidato;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Candidato candidato) {
        try{
            repositorioCandidato.save(candidato);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
}
