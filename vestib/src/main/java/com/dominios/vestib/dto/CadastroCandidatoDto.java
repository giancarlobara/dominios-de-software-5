package com.dominios.vestib.dto;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.model.Pessoa;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
@Data
public class CadastroCandidatoDto {
    private long id;

    private String codigo;

    private Curso curso;

    private Pessoa pessoa;

    private int situacao;

    private int inscricao;

    private double notaFinal;

    private int numAcertos;

    private int jurado;

    private String nome;

    private String cpf;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNasc;
}
