package com.dominios.vestib.controller;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Csv.CsvCandidato;
import com.dominios.vestib.model.Csv.CsvCartaoResposta;
import com.dominios.vestib.model.Csv.LogCartaoResposta;
import com.dominios.vestib.model.Curso;
import com.dominios.vestib.model.Pessoa;
import com.dominios.vestib.service.ServicoCandidato;
import com.dominios.vestib.service.ServicoCsvCandidato;
import com.dominios.vestib.service.ServicoPessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RequestMapping("candidatos")
@Controller
public class ControleCandidato {
    private final ServicoCsvCandidato servicoCsvCandidato;
    private final ServicoPessoa servicoPessoa;
    private final ServicoCandidato servicoCandidato;
    private final ObjectMapper objectMapper;
    public ControleCandidato(ServicoCsvCandidato servicoCsvCandidato, ServicoPessoa servicoPessoa, ServicoCandidato servicoCandidato, ObjectMapper objectMapper) {
        this.servicoCsvCandidato = servicoCsvCandidato;
        this.servicoPessoa = servicoPessoa;
        this.servicoCandidato = servicoCandidato;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Candidato candidato) {
        try{
            servicoCandidato.save(candidato);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
    @GetMapping("/list/{idCurso}")
    public String getCandidatos(Model model,@PathVariable long idCurso) {
        model.addAttribute("candidatos",servicoCandidato.getByCurso(idCurso));
        return "lista-candidatos";
    }

    @GetMapping ("/get/upload/csv/{idCurso}")
    public String uploadCsv(Model model, @PathVariable Long idCurso) {
        model.addAttribute("idCurso", idCurso);
        return "/importar-candidatos";
    }
    @PostMapping("/upload/csv/{idCurso}")
    public String uploadCsv(@RequestParam("file") MultipartFile file,@PathVariable Long idCurso) throws Exception {
        servicoCsvCandidato.createFileTemp(Path.of("/opt/file.csv"),file.getBytes());
        List<CsvCandidato> csvCandidatos = servicoCsvCandidato.readCsv(Path.of("/opt/file.csv"));
        Files.deleteIfExists(Path.of("/opt/file.csv"));
        for(CsvCandidato csvCandidato : csvCandidatos){
            Pessoa pessoa = Pessoa.transformImModel(csvCandidato);

            Long idPessoa = servicoPessoa.save(pessoa);
            Candidato candidato = Candidato.transformImModel(csvCandidato);
            candidato.setPessoa(new Pessoa(idPessoa));
            candidato.setCurso(new Curso(idCurso));
            servicoCandidato.save(candidato);
        }
        return "redirect:/cursos/list/" + idCurso;
    }

    @GetMapping ("/get/upload/csv/cartao/{idCurso}")
    public String uploadCsvCartaoResposta(Model model, @PathVariable Long idCurso) {
        model.addAttribute("idCurso", idCurso);
        return "/importar-cartao-resposta";
    }
    @RequestMapping("/upload/csv/cartao/{idCurso}")
    public ModelAndView uploadCartaoRespostaCsv(ModelMap model,
                                                @RequestParam("file") MultipartFile file,
                                                @PathVariable Long idCurso,
                                                HttpServletRequest request,
                                                final RedirectAttributes redirectAttributes) throws Exception {

        servicoCsvCandidato.createFileTemp(Path.of("/opt/file.csv"),file.getBytes());
        List<CsvCartaoResposta> csvCRS = servicoCsvCandidato.readCsvCartaoResposta(Path.of("/opt/file.csv"));
        Files.deleteIfExists(Path.of("/opt/file.csv"));
        List<LogCartaoResposta> log = servicoCandidato.RegisterCandidatos(csvCRS,idCurso);
        List<Candidato> candidatos = servicoCandidato.getCandidatosSemImagem(idCurso);
        if(!candidatos.isEmpty()){
            candidatos.forEach(c -> {
                log.add(new LogCartaoResposta(c.getCodigo(),"Candidato sem imagem do cartão resposta"));
            });
        }
        return new ModelAndView("redirect:/cursos/list/"+idCurso);


//        redirectAttributes.addAttribute("log","teste");
//        model.addAttribute("loger",new ArrayList<LogCartaoResposta>().addAll(log));
//        String url = "forward:/cursos/list/" + idCurso;
//        return new RedirectView("forward:/cursos/list/"+idCurso);
    }
}
