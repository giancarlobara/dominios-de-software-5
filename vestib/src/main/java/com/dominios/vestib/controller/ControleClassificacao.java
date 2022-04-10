package com.dominios.vestib.controller;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Classificacao;
import com.dominios.vestib.model.CriterioDesempate;
import com.dominios.vestib.model.Curso;
import com.dominios.vestib.service.ServicoCandidato;
import com.dominios.vestib.service.ServicoClassificacao;
import com.dominios.vestib.service.ServicoCurso;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("classificacao")
@Controller
public class ControleClassificacao {
    private final ServicoClassificacao servicoClassificacao;
    private final ServicoCandidato servicoCandidato;
    private final ServicoCurso servicoCurso;

    public ControleClassificacao(ServicoClassificacao servicoClassificacao, ServicoCandidato servicoCandidato, ServicoCurso servicoCurso) {
        this.servicoClassificacao = servicoClassificacao;
        this.servicoCandidato = servicoCandidato;
        this.servicoCurso = servicoCurso;
    }

    @GetMapping("/generate/{idCurso}")
    public String get(Model model, @PathVariable long idCurso) {
        Curso curso = servicoCurso.getById(idCurso);
        if(curso != null) {
            servicoCandidato.calculate(curso);
            List<Candidato> candidatos = servicoCandidato.getByCurso(idCurso);
            candidatos = CriterioDesempate.compara(curso.getCriterio(),candidatos);
            int cont = 1;
            if (candidatos != null) {
                for(int i = candidatos.size()-1; i>=0;i--){
                    Classificacao classificacao = new Classificacao();
                    classificacao.setCurso(curso);
                    classificacao.setCandidato(candidatos.get(i));
                    classificacao.setPosicao(cont);
                    classificacao.setTipoVaga("AC");
                    cont = cont + 1;
                    servicoClassificacao.save(classificacao);
                }
            }
            return "redirect:/cursos/list";
        }
        return "redirect:/cursos/list?error=true";
    }
}
