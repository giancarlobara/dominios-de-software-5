package com.dominios.vestib.model.Csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvCandidato extends CsvBean{
    @CsvBindByName(column = "nome")
    private String nome;

    @CsvBindByName
    private String codigo;

    @CsvBindByName
    private int situacao;

    @CsvBindByName
    private int inscricao;

    private int jurado;

    @CsvBindByName
    private String cpf;

    @CsvBindByName
    @CsvDate(value = "dd/MM/yyyy")
    private LocalDate dataNasc;

    public CsvCandidato() {
    }

    public CsvCandidato(String nome, String codigo, int situacao, int inscricao, String cpf) {
        this.nome = nome;
        this.codigo = codigo;
        this.situacao = situacao;
        this.inscricao = inscricao;
        this.cpf = cpf;
    }

    public CsvCandidato(String nome, String codigo, int situacao, int inscricao, int jurado, String cpf, LocalDate dataNasc) {
        this.nome = nome;
        this.codigo = codigo;
        this.situacao = situacao;
        this.inscricao = inscricao;
        this.jurado = jurado;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public CsvCandidato(String nome, String codigo, int situacao, int inscricao, String cpf, LocalDate dataNasc) {
        this.nome = nome;
        this.codigo = codigo;
        this.situacao = situacao;
        this.inscricao = inscricao;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }
}
