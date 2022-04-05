package com.dominios.vestib.model.Csv;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvCartaoResposta extends CsvBean{
    @CsvBindByName(column = "NUMG_CandidatoBC")
    private String codigoCandidatoCb;
    @CsvBindByName(column = "NUMG_Candidato")
    private String codigoCandidato;
    @CsvBindByName(column = "Ausente")
    private int ausente;
    @CsvBindAndJoinByName(column = ".*",elementType = String.class)
    private MultiValuedMap<String, String> questoes;
    @CsvBindByName(column = "BatchPgDta")
    private String nomeImagem;
}
