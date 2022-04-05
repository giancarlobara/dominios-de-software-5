package com.dominios.vestib.controller;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Csv.CsvCandidato;
import com.dominios.vestib.model.Csv.CsvBean;
import com.dominios.vestib.model.Csv.CsvCartaoResposta;
import com.dominios.vestib.model.Curso;
import com.dominios.vestib.model.Pessoa;
import com.dominios.vestib.repository.RepositorioCandidato;
import com.dominios.vestib.service.ServicoCandidato;
import com.dominios.vestib.service.ServicoCsvCandidato;
import com.dominios.vestib.service.ServicoPessoa;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("candidatos")
@Controller
public class ControleCandidato {
    private final RepositorioCandidato repositorioCandidato;
    private final ServicoCsvCandidato servicoCsvCandidato;
    private final ServicoPessoa servicoPessoa;
    private final ServicoCandidato servicoCandidato;

    public ControleCandidato(RepositorioCandidato repositorioCandidato, ServicoCsvCandidato servicoCsvCandidato, ServicoPessoa servicoPessoa, ServicoCandidato servicoCandidato) {
        this.repositorioCandidato = repositorioCandidato;
        this.servicoCsvCandidato = servicoCsvCandidato;
        this.servicoPessoa = servicoPessoa;
        this.servicoCandidato = servicoCandidato;
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
    @GetMapping("/upload/csv")
    public void writeCsvFromBean() throws Exception {
        List<CsvBean> list = new ArrayList<>();
        list.add(new CsvCandidato("flavimar", "cod01", 1,1,"17202273475"));
        list.add(new CsvCandidato("giancarlo", "cod02", 2,1,"14202277475"));
        Writer writer = Files.newBufferedWriter(Paths.get("/pessoas.csv"));
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        sbc.write(list);
        writer.flush();
        writer.close();
    }
    @GetMapping ("/get/upload/csv/cartao/{idCurso}")
    public String uploadCsvCartaoResposta(Model model, @PathVariable Long idCurso) {
        model.addAttribute("idCurso", idCurso);
        return "/importar-cartao-resposta";
    }
    @PostMapping("/upload/csv/cartao/{idCurso}")
    public String uploadCartaoRespostaCsv(Model model,@RequestParam("file") MultipartFile file,@PathVariable Long idCurso) throws Exception {
        servicoCsvCandidato.createFileTemp(Path.of("/opt/file.csv"),file.getBytes());
        List<CsvCartaoResposta> csvCRS = servicoCsvCandidato.readCsvCartaoResposta(Path.of("/opt/file.csv"));
        Files.deleteIfExists(Path.of("/opt/file.csv"));

        List<String> ausentes = new ArrayList<>();
        for(CsvCartaoResposta cr : csvCRS){
            Optional<Candidato> optionalCandidato = servicoCandidato.getCandidatoByCodigo(cr.getCodigoCandidato(),idCurso);
            if(optionalCandidato.isPresent()) {
                Candidato candidato = optionalCandidato.get();
                if (cr.getAusente() != 0) {
                    Collection<Map.Entry<String, String>> resp = cr.getQuestoes().entries();
                    String gabarito = servicoCandidato.formatGabarito(cr.getQuestoes().entries());
                    candidato.setCartaoResposta(gabarito);
                }
                else{
                    ausentes.add(cr.getCodigoCandidato());
                }
                candidato.setSituacao(cr.getAusente());
                candidato.setNomeImagem(cr.getNomeImagem());
                servicoCandidato.save(candidato);
            }
        }
        model.addAttribute(ausentes);
        return "redirect:/cursos/list/" + idCurso;
    }
}
