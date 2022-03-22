package com.dominios.vestib.model;

public enum Escolaridade{
    F("FUNDAMENTAL"),
    M("MÉDIO"),
    S("SUPERIOR");

    private final String valor;

    private Escolaridade(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
