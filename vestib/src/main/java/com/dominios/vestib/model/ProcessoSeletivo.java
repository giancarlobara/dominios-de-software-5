package com.dominios.vestib.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProcessoSeletivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
