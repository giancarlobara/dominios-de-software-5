package com.dominios.vestib.controller;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.service.ServicoCurso;
import com.dominios.vestib.service.ServicoDisciplina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RequestMapping("cursos")
@Controller("controle_curso")
public class ControleCurso {
    private final ServicoDisciplina servicoDisciplina;
    private final ServicoCurso servicoCurso;

    public ControleCurso(ServicoDisciplina servicoDisciplina, ServicoCurso servicoCurso)     {
        this.servicoDisciplina = servicoDisciplina;

        this.servicoCurso = servicoCurso;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Curso curso) {
        try{
            servicoCurso.save(curso);
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

    @RequestMapping("/list/{id}")
    public String getCurso(Model model, @PathVariable Long id,RedirectAttributes redirectAttributes, HttpServletRequest request){
        model.addAttribute("disciplinas",servicoDisciplina.getByCurso(id));
        Curso curso = servicoCurso.getById(id);
        if(curso != null) {
            model.addAttribute("curso", curso);
            return "/visualizar-curso";
        }
        return "redirect:/cursos/list?error=true";
    }
    @GetMapping("/list")
    public String getListCursos(Model model){
        model.addAttribute("cursos",servicoCurso.getAllOrdered());
        return "/lista-cursos";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
       servicoCurso.delete(id);
        return "redirect:/cursos/list";
    }

}
