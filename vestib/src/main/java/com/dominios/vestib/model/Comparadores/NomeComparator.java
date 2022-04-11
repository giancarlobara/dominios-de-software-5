package com.dominios.vestib.model.Comparadores;

import com.dominios.vestib.model.Candidato;

import java.util.Comparator;

public class NomeComparator  implements Comparator<Candidato> {

    @Override
    public int compare(Candidato c1, Candidato c2) {
        return c1.getPessoa().getNome().compareTo(c2.getPessoa().getNome());
    }
}
