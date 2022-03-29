package com.dominios.vestib.controller;

import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.repository.RepositorioDisciplina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

@RequestMapping("disciplinas")
@Controller("controle-disciplina")
public class ControleDisciplina {
    private final RepositorioDisciplina repositorioDisciplina;

    public ControleDisciplina(RepositorioDisciplina repositorioDisciplina) {
        this.repositorioDisciplina = repositorioDisciplina;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Disciplina disciplina) {
        try{
            repositorioDisciplina.save(disciplina);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
    @GetMapping("/add")
    public String getAddDisciplina(Model model) {

        model.addAttribute("disciplina", new Disciplina());
        return "adicionar-curso";
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
