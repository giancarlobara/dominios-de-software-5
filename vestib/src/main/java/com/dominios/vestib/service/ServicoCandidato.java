package com.dominios.vestib.service;

import com.dominios.vestib.model.Candidato;
import com.dominios.vestib.model.Csv.CsvCartaoResposta;
import com.dominios.vestib.model.Csv.LogCartaoResposta;
import com.dominios.vestib.model.Curso;
import com.dominios.vestib.repository.RepositorioCandidato;
import com.dominios.vestib.utils.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class ServicoCandidato {
    private final RepositorioCandidato repositorioCandidato;
    private final ServicoPessoa servicoPessoa;
    private final ServicoCurso servicoCurso;

    public ServicoCandidato(RepositorioCandidato repositorioCandidato, ServicoPessoa servicoPessoa, ServicoCurso servicoCurso) {
        this.repositorioCandidato = repositorioCandidato;
        this.servicoPessoa = servicoPessoa;
        this.servicoCurso = servicoCurso;
    }
    public Long save(Candidato candidato){
        Optional<Candidato> optionalCandidato = repositorioCandidato.findByCodigo(candidato.getCodigo());

        optionalCandidato.ifPresent(value -> {
            if(!Objects.equals(value.getCurso().getId(), candidato.getCurso().getId())){
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"Codigo do candidato já existe em outro curso");
            }
            candidato.setId(value.getId());
        });
        repositorioCandidato.save(candidato);

        return candidato.getId();
    }
    public List<Candidato> getByCurso(Long idCurso){
        return repositorioCandidato.findByCursoId(idCurso);
    }
    public Optional<Candidato> getCandidatoByCodigo(String codigo,Long idCurso){
        return repositorioCandidato.findByCodigoAndCursoId(codigo,idCurso);
    }
    public String formatGabarito(Collection<Map.Entry<String, String>> entries){
        String gabarito = "";
        Map<Integer, String> questoes = new TreeMap<>();
        for (Map.Entry<String, String> entry : entries) {
            int key = Integer.parseInt(entry.getKey().replaceAll("[\\D]", ""));
            String value = entry.getValue();
            questoes.put(key, value);
        }
        Collection<String> values = questoes.values();
        for (String v : values) {
            gabarito = gabarito.concat(v);
        }
        gabarito = gabarito.replaceAll("_", "");
        System.out.println(gabarito);
        return gabarito;
    }
    public CsvCartaoResposta validateCsvCartaoResposta(CsvCartaoResposta cr,String gabarito) throws ResponseStatusException{
        if(StringUtil.isNullOrEmpty(cr.getNomeImagem())){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Imagem do cartão vazia");
        }
        if(cr.getAusente() == 1){
            if(!StringUtil.isNullOrEmpty(gabarito)){
                cr.setAusente(0);
            }

        }else{
            if(StringUtil.isNullOrEmpty(gabarito)){
                cr.setAusente(1);
            }
        }
        return cr;
    }
    public List<Candidato> getCandidatosSemImagem(long idCurso){
        List<Candidato> candidatos = repositorioCandidato.findByCursoIdAndNomeImagemIsNull(idCurso);
        ArrayList<LogCartaoResposta> log = new ArrayList<>();
        return candidatos;
    }
    public ArrayList<LogCartaoResposta> RegisterCandidatos(List<CsvCartaoResposta> csvCRS,Long idCurso){
        ArrayList<LogCartaoResposta> log = new ArrayList<>();
        for(CsvCartaoResposta cr : csvCRS){
            Optional<Candidato> optionalCandidato = getCandidatoByCodigo(cr.getCodigoCandidato(),idCurso);
            if(optionalCandidato.isPresent()) {
                Candidato candidato = optionalCandidato.get();
                String gabarito = formatGabarito(cr.getQuestoes().entries());
                try {
                    cr = validateCsvCartaoResposta(cr, gabarito);
                }catch (ResponseStatusException e){
                    log.add(new LogCartaoResposta(cr.getCodigoCandidato(),e.getReason()));
                }
                candidato.setCartaoResposta(gabarito);
                candidato.setSituacao((cr.getAusente() == 1)? 0 : 1);
                candidato.setNomeImagem(cr.getNomeImagem());
                save(candidato);
            }else{
                log.add(new LogCartaoResposta(cr.getCodigoCandidato(),"Candidato não existe ou codigo incorreto"));
            }
        }
        return log;
    }
    public double calculateNota(String gabarito, String cr){
        int acertos = 0;
        DecimalFormat df = new DecimalFormat("##0.00");
        if(!StringUtil.isNullOrEmpty(cr)) {
            for (int i = 0; i < gabarito.length() && i < cr.length() ; i++) {
                if (gabarito.charAt(i) == cr.charAt(i)) {
                    acertos++;
                }
            }
        }
        double nota;
        if (acertos > 0){
           String aux = df.format(((double) gabarito.length() / acertos )).replace(",",".");
            nota = Double.parseDouble(aux);
        }else{
            nota = 0.00;
        }
        return nota;
    }
    public void calculate(Curso curso){
        List<Candidato> candidatos = repositorioCandidato.findByCursoId(curso.getId());
        for (Candidato candidato : candidatos){
            candidato.setNotaFinal(calculateNota(curso.getGabarito(),candidato.getCartaoResposta()));
            save(candidato);
        }
    }
    public void calculate(long idCurso) {
        Curso curso = servicoCurso.getById(idCurso);
        if (curso != null) {
            List<Candidato> candidatos = repositorioCandidato.findByCursoId(idCurso);
            for (Candidato candidato : candidatos) {
                    candidato.setNotaFinal(calculateNota(curso.getGabarito(), candidato.getCartaoResposta()));
                    save(candidato);

            }
        }
    }
}
