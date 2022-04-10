package com.dominios.vestib.model.Csv;

import com.opencsv.bean.CsvBindByName;

public class LogCartaoResposta extends CsvBean{
    @CsvBindByName(column = "Codigo")
    String codigo;
    @CsvBindByName(column = "Exceção")
    String motivo;

    public LogCartaoResposta(String codigoCandidato, String reason) {
        this.codigo = codigoCandidato;
        this.motivo = reason;
    }
}
