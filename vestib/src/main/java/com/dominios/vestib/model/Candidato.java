package com.dominios.vestib.model;

import lombok.Data;

import javax.persistence.*;

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

    @JoinColumn(nullable = false)
    @ManyToOne
    private Pessoa pessoa;

//    private String cartaoResposta;

    private int situacao;

    private int inscricao;

    private double notaFinal;

    private int numAcertos;

    private int jurado;
    //    @Transient
//    private int classificacaoAC;
    //    @Transient
//    private int classificacaoPcd;


}
