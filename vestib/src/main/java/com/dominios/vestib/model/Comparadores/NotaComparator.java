package com.dominios.vestib.model.Comparadores;

import com.dominios.vestib.model.Candidato;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class NotaComparator implements Comparator<Candidato>{

    @Override
    public int compare(Candidato pessoa1, Candidato pessoa2) {
        return Double.compare(pessoa1.getNotaFinal(),pessoa2.getNotaFinal());
    }
}
