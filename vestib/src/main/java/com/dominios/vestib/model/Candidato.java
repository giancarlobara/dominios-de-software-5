package com.dominios.vestib.model;

import com.dominios.vestib.model.Csv.CsvCandidato;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true,nullable = false)
    private String codigo;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Curso curso;

    @JoinColumn(nullable = true)
    @ManyToOne
    private Pessoa pessoa;

    private String cartaoResposta;

    private String nomeImagem;

    @Column(columnDefinition = "int default 1")
    private int situacao;

    private int inscricao;

    private double notaFinal;

    private int numAcertos;

    private int jurado;
    //    @Transient
//    private int classificacaoAC;
    //    @Transient
//    private int classificacaoPcd;

    public Candidato(CsvCandidato candidato) {
        this.codigo = candidato.getCodigo();
        this.situacao = candidato.getSituacao();
        this.inscricao = candidato.getInscricao();
        this.jurado = candidato.getJurado();

    }

    public Candidato() {
    }

    public static Candidato transformImModel(CsvCandidato csvCandidato) {
        return new Candidato(csvCandidato);
    }
    public static List<Candidato> convert(List<CsvCandidato> csvCandidatos) {
        return csvCandidatos.stream().map(Candidato::new).collect(Collectors.toList());
    }
}
