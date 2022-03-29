package com.dominios.vestib.controller;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Csv.CandidatoCsv;
import com.dominios.vestib.model.Csv.CsvBean;
import com.dominios.vestib.repository.RepositorioCandidato;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.PersistenceException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("candidatos")
@Controller
public class ControleCandidato {
    private final RepositorioCandidato repositorioCandidato;

    public ControleCandidato(RepositorioCandidato repositorioCandidato) {
        this.repositorioCandidato = repositorioCandidato;
    }

    @PostMapping("/add")
    public String put(@ModelAttribute Candidato candidato) {
        try{
            repositorioCandidato.save(candidato);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
    @PostMapping("/upload/csv/{idCurso}")
    public String uploadCsv(@ModelAttribute Candidato candidato, Long idCurso) {
        try{
            repositorioCandidato.save(candidato);
        }catch (PersistenceException e){
            return "redirect:/adicionar-curso?error=true";
        }
        return "redirect:/cursos/list";
    }
    @GetMapping("/upload/csv")
    public void writeCsvFromBean() throws Exception {
        List<CsvBean> list = new ArrayList<>();
        list.add(new CandidatoCsv("flavimar", "cod01", 1,1,"17202273475"));
        list.add(new CandidatoCsv("giancarlo", "cod02", 2,1,"14202277475"));
        Writer writer = Files.newBufferedWriter(Paths.get("/opt/pessoas.csv"));
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();


        sbc.write(list);

        writer.flush();
        writer.close();
    }
}
