package com.dominios.vestib.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Classificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int posicao;
    @Column(columnDefinition = "varchar(255) default 'AC'")
    private String tipoVaga;
    @ManyToOne
    private Candidato candidato;
    @ManyToOne
    private Curso curso;
//    @ManyToOne
//    private ProcessoSeletivo processoSeletivo;
}
