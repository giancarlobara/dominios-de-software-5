package com.dominios.vestib.model.Csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CandidatoCsv extends CsvBean{
    @CsvBindByName(column = "nome")
    private String name;

    @CsvBindByName
    private String codigo;

    @CsvBindByName
    private int situacao;

    @CsvBindByName
    private int inscricao;

    @CsvBindByName
    private String cpf;

    @CsvBindByName
    @CsvDate("dd/MM/yyyy")
    private LocalDate dataNasc;

    public CandidatoCsv() {
    }

    public CandidatoCsv(String name, String codigo, int situacao, int inscricao, String cpf) {
        this.name = name;
        this.codigo = codigo;
        this.situacao = situacao;
        this.inscricao = inscricao;
        this.cpf = cpf;
    }

    public CandidatoCsv(String name, String codigo, int situacao, int inscricao, String cpf, LocalDate dataNasc) {
        this.name = name;
        this.codigo = codigo;
        this.situacao = situacao;
        this.inscricao = inscricao;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }
}
