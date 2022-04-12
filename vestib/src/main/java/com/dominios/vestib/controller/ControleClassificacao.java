package com.dominios.vestib.controller;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Classificacao;
import com.dominios.vestib.model.CriterioDesempate;
import com.dominios.vestib.model.Curso;
import com.dominios.vestib.service.ServicoCandidato;
import com.dominios.vestib.service.ServicoClassificacao;
import com.dominios.vestib.service.ServicoCurso;
import com.dominios.vestib.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
            List<Candidato> candidatos = servicoCandidato.getByCurso(idCurso);
            candidatos = CriterioDesempate.compara(curso.getCriterio(),candidatos);
            int cont = 1;
            if (candidatos != null) {
                for(int i = candidatos.size()-1; i>=0;i--){
                    if (!StringUtil.isNullOrEmpty(candidatos.get(i).getNomeImagem()) && candidatos.get(i).getSituacao() != 0) {
                        Classificacao classificacao = new Classificacao();
                        Classificacao optionalClass = servicoClassificacao.getClassificacaoByCandidato(candidatos.get(i).getId());
                        if(optionalClass != null) {
                            classificacao.setId(optionalClass.getId());
                        }
                        classificacao.setCurso(curso);
                        classificacao.setCandidato(candidatos.get(i));
                        classificacao.setPosicao(cont);
                        classificacao.setTipoVaga("AC");
                        cont = cont + 1;
                        servicoClassificacao.save(classificacao);
                    }
                }
            }
            List<Classificacao> classificacao = servicoClassificacao.getClassificacao(idCurso);
            if(classificacao.isEmpty()){
                return "redirect:/cursos/list/"+idCurso+"/?error=true";
            }
            model.addAttribute("curso",curso);
            model.addAttribute("classificacao",classificacao);
            return "classificacao-candidatos";
        }
        return "redirect:/cursos/list/"+idCurso+"/?error=true";
    }
}
