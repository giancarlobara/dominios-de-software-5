package com.dominios.vestib.controller;

import com.dominios.vestib.model.Pessoa;
import com.dominios.vestib.repository.RepositorioPessoa;
import com.dominios.vestib.service.ServicoPessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

@RequestMapping("pessoas")
@Controller("controle-pessoa")
public class ControlePessoa {

    private final ServicoPessoa servicoPessoa;

    public ControlePessoa(ServicoPessoa servicoPessoa) {
        this.servicoPessoa = servicoPessoa;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Pessoa pessoa) {
        try{
            servicoPessoa.save(pessoa);
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
        model.addAttribute("pessoas",servicoPessoa.getAll());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        servicoPessoa.delete(id);
        return "redirect:/pessoas/list";
    }
}
