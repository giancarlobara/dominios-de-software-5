package com.dominios.vestib.controller;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.repository.RepositorioCurso;
import com.dominios.vestib.service.ServicoDisciplina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.persistence.PersistenceException;
import java.util.Optional;


@RequestMapping("cursos")
@Controller("controle_curso")
public class ControleCurso {
    private final RepositorioCurso repositorioCurso;
    private final ServicoDisciplina servicoDisciplina;

    public ControleCurso(RepositorioCurso repositorioAreaEnsino, ServicoDisciplina servicoDisciplina) {
        this.repositorioCurso = repositorioAreaEnsino;
        this.servicoDisciplina = servicoDisciplina;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Curso curso) {
        try{
            repositorioCurso.save(curso);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/disciplinas/add/" + curso.getId();
    }
    @GetMapping("/add")
    public String getAddCurso(Model model) {

        model.addAttribute("curso", new Curso());
        return "adicionar-curso";
    }

    @GetMapping("/list/{id}")
    public String getCurso(Model model,@PathVariable Long id){
        model.addAttribute("disciplinas",servicoDisciplina.getByCurso(id));
        Optional<Curso> curso = repositorioCurso.findById(id);
        if(curso.isPresent()) {
            model.addAttribute("curso", curso.get());
            return "/visualizar-curso";
        }
        return "redirect:/cursos/list?error=true";
    }
    @GetMapping("/list")
    public String getListCursos(Model model){
        model.addAttribute("cursos",repositorioCurso.findAllOrderByCodigo());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        servicoDisciplina.deleteAllByCurso(id);
        repositorioCurso.deleteById(id);
        return "redirect:/cursos/list";
    }

}
