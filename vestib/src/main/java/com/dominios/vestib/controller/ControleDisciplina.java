package com.dominios.vestib.controller;

import com.dominios.vestib.dto.addDisciplinasDto;
import com.dominios.vestib.model.Curso;
import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.repository.RepositorioDisciplina;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("disciplinas")
@Controller("controle-disciplina")
public class ControleDisciplina {
    private final RepositorioDisciplina repositorioDisciplina;

    public ControleDisciplina(RepositorioDisciplina repositorioDisciplina) {
        this.repositorioDisciplina = repositorioDisciplina;
    }

    @PostMapping("/add/{idCurso}")
    public String put(@ModelAttribute Disciplina disciplina,@PathVariable Long idCurso) {
        try{
            disciplina.setCurso(new Curso(idCurso));
            repositorioDisciplina.save(disciplina);
        }catch (PersistenceException e){
            return "redirect:/disciplinas/add/" + idCurso + "?error=true";
        }
        return "redirect:/disciplinas/add/" + idCurso;
    }

    @GetMapping("/add/{idCurso}")
    public String getAddDisciplina(Model model,@PathVariable Long idCurso) {
         model.addAttribute("disciplina",new Disciplina());
         model.addAttribute("idCurso",idCurso);
        return "/adicionar-disciplina";
    }

    @GetMapping("/list")
    public String getListDisciplina(Model model){
        model.addAttribute("disciplinas",repositorioDisciplina.findAll());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        repositorioDisciplina.deleteById(id);
        return "redirect:/disciplinas/list";
    }

}
