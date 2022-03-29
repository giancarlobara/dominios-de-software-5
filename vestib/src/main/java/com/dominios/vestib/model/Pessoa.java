package com.dominios.vestib.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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
}
