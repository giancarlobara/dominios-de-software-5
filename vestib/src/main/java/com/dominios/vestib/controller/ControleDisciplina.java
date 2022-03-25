package com.dominios.vestib.controller;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.repository.RepositorioDisciplina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;

@Controller
public class ControleDisciplina {
    private final RepositorioDisciplina repositorioDisciplina;

    public ControleDisciplina(RepositorioDisciplina repositorioDisciplina) {
        this.repositorioDisciplina = repositorioDisciplina;
    }

    @PostMapping("add")
    public String put(@ModelAttribute Disciplina disciplina) {
        try{
            repositorioDisciplina.save(disciplina);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
    @GetMapping("/add")
    public String getAddCurso(Model model) {

        model.addAttribute("curso", new Curso());
        return "adicionar-curso";
    }

    @GetMapping("/list")
    public String getListCursos(Model model){
        model.addAttribute("cursos",repositorioDisciplina.findAll());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        repositorioDisciplina.deleteById(id);
        return "redirect:/cursos/list";
    }

}
