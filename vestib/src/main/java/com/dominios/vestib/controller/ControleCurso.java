package com.dominios.vestib.controller;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.repository.RepositorioCurso;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.persistence.PersistenceException;


@RequestMapping("cursos")
@Controller("controle_area_ensino")
public class ControleCurso {
    private final RepositorioCurso repositorioCurso;

    public ControleCurso(RepositorioCurso repositorioAreaEnsino) {
        this.repositorioCurso = repositorioAreaEnsino;
    }

    @PostMapping("add")
    public String put(@ModelAttribute Curso curso) {
        try{
            repositorioCurso.save(curso);
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

    @GetMapping
    public Iterable<Curso> getAll(@ModelAttribute Long id) {
        return repositorioCurso.findAll();
    }
    @GetMapping("/list")
    public String getListCursos(Model model){
        model.addAttribute("cursos",repositorioCurso.findAllOrderByCodigo());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        repositorioCurso.deleteById(id);
        return "redirect:/cursos/list";
    }

}
