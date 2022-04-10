package com.dominios.vestib.model.Comparadores;

import com.dominios.vestib.model.Candidato;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class IdadeComparator implements Comparator<Candidato> {


    @Override
    public int compare(Candidato pessoa1, Candidato pessoa2) {
        NotaComparator nota = new NotaComparator();
        int cmp = nota.compare(pessoa1,pessoa2);
        if(cmp == 0){
        LocalDate agora = LocalDate.now();
        return Integer.compare(Period.between(pessoa1.getPessoa().getDataNasc(), agora).getYears()
                , Period.between(pessoa2.getPessoa().getDataNasc(), agora).getYears());
        }
        return cmp;
    }

}
