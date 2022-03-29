package com.dominios.vestib.controller;

import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.model.Pessoa;
import com.dominios.vestib.repository.RepositorioPessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

@RequestMapping("pessoas")
@Controller("controle-pessoa")
public class ControlePessoa {
    private final RepositorioPessoa repositorioPessoa;

    public ControlePessoa(RepositorioPessoa repositorioPessoa) {
        this.repositorioPessoa = repositorioPessoa;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Pessoa pessoa) {
        try{
            repositorioPessoa.save(pessoa);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
    @GetMapping("/add")
    public String getAddPessoa(Model model) {

        model.addAttribute("Pessoa", new Pessoa());
        return "adicionar-curso";
    }

    @GetMapping("/list")
    public String getListPessoa(Model model){
        model.addAttribute("pessoas",repositorioPessoa.findAll());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        repositorioPessoa.deleteById(id);
        return "redirect:/pessoas/list";
    }
}
