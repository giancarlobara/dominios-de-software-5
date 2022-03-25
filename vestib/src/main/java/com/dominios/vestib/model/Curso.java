package com.dominios.vestib.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true,nullable = false)
    private String codigo;

    private String nome;

    private String funcao;

    private int vagas;

    private int vagasPne;

    private int vagasCota;

    private String gabarito;

    private double notaMinima;

    private String grupo;

    private String cidadeLocacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Escolaridade nivel;

    public Curso() {
    }

}
