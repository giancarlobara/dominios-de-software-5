package com.dominios.vestib.controller;

import com.dominios.vestib.model.AreaEnsino;
import com.dominios.vestib.repository.RepositorioAreaEnsino;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.util.ArrayList;


@RequestMapping("ensino")
@Controller("controle_area_ensino")
public class ControleAreaEnsino {
    private final RepositorioAreaEnsino repositorioAreaEnsino;

    public ControleAreaEnsino(RepositorioAreaEnsino repositorioAreaEnsino) {
        this.repositorioAreaEnsino = repositorioAreaEnsino;
    }

    @PostMapping("add")
    public String put(@ModelAttribute AreaEnsino areaEnsino) {
        try{
            repositorioAreaEnsino.save(areaEnsino);
        }catch (PersistenceException e){
            return "redirect:/adicionar-area-ensino?error=true";
        }
        return "redirect:/ensino/list";
    }
    @GetMapping("/add")
    public String getAddAreaEnsino(Model model) {

        model.addAttribute("areaEnsino", new AreaEnsino());
        return "adicionar-area-ensino";
    }

    @GetMapping
    public Iterable<AreaEnsino> getAll(@ModelAttribute Long id) {
        return repositorioAreaEnsino.findAll();
    }
    @GetMapping("/list")
    public String getAdminListFeatured(Model model) throws ParseException {
        model.addAttribute("ensinos",(ArrayList<AreaEnsino>)repositorioAreaEnsino.findAll());
        return "/lista-area-ensino";
    }

}
