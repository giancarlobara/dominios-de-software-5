package com.dominios.vestib.model;

import com.dominios.vestib.model.Csv.CsvCandidato;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Column(unique=true,nullable = false)
    private String cpf;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNasc;

    @Transient
    private boolean pne;
    @Transient
    private boolean negro;
    @Transient
    private boolean cor;

    public Pessoa() {
    }

    public Pessoa(CsvCandidato candidato) {
        this.nome = candidato.getNome();
        this.cpf = candidato.getCpf();
        this.dataNasc = candidato.getDataNasc();

    }

    public Pessoa(Long idPessoa) {
        this.id = idPessoa;
    }

    public static Pessoa transformImModel(CsvCandidato csvCandidato) {
        return new Pessoa(csvCandidato);
    }
    public static List<Pessoa> convert(List<CsvCandidato> csvCandidatos) {
        return csvCandidatos.stream().map(Pessoa::new).collect(Collectors.toList());
    }
}
