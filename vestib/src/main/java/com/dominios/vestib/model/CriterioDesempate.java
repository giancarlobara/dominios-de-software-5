package com.dominios.vestib.model;

import com.dominios.vestib.model.Comparadores.IdadeComparator;

import java.util.Comparator;
import java.util.List;

public enum CriterioDesempate{
    IDADE;

    public static List<Candidato> compara(CriterioDesempate criterioDesempate, List<Candidato> candidatos){
        switch (criterioDesempate){
            case IDADE:
                return comparaIdade(candidatos);
        }
        return null;
    }
    public static List<Candidato> comparaIdade(List<Candidato> candidatos){
        candidatos.sort(new IdadeComparator());
        return candidatos;
    }




}
