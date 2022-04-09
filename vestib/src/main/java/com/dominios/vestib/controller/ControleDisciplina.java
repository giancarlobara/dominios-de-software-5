package com.dominios.vestib.controller;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.repository.RepositorioDisciplina;
import com.dominios.vestib.service.ServicoDisciplina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

@RequestMapping("disciplinas")
@Controller("controle-disciplina")
public class ControleDisciplina {
    private final ServicoDisciplina servicoDisciplina;
    public ControleDisciplina(ServicoDisciplina servicoDisciplina) {
        this.servicoDisciplina = servicoDisciplina;
    }

    @PostMapping("/add/{idCurso}")
    public String put(@ModelAttribute Disciplina disciplina,@PathVariable Long idCurso) {
        try{

            disciplina.setCurso(new Curso(idCurso));
            servicoDisciplina.save(disciplina);
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
        model.addAttribute("disciplinas",servicoDisciplina.getAll());
        return "/visualizar-curso";
    }
    @PostMapping({"/remove"})
    public String remove(@RequestParam long id) {
        servicoDisciplina.delete(id);
        return "redirect:/disciplinas/list";
    }

}
