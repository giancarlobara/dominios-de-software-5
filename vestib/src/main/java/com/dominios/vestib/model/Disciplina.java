package com.dominios.vestib.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private double peso;

    private String gabarito;

    private int questaoInicial;

    private int questaoFinal;

    @ManyToOne
    private Curso areaEnsino;


}
