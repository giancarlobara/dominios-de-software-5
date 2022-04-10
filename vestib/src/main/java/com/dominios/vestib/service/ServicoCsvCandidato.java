package com.dominios.vestib.service;

import com.dominios.vestib.model.Csv.CsvCandidato;
import com.dominios.vestib.model.Csv.CsvCartaoResposta;
import com.dominios.vestib.model.Csv.LogCartaoResposta;
import com.dominios.vestib.repository.RepositorioCandidato;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ServicoCsvCandidato {
    private final RepositorioCandidato repositorioCandidato;

    public ServicoCsvCandidato(RepositorioCandidato repositorioCandidato) {
        this.repositorioCandidato = repositorioCandidato;
    }

    public void createFileTemp(Path pathFile,byte[] bytes) throws IOException {
        OutputStream fileTemp;
        fileTemp = java.nio.file.Files.newOutputStream(pathFile);
        fileTemp.write(bytes);
        fileTemp.flush();
        fileTemp.close();
    }
    public List<CsvCandidato> readCsv(Path path) throws IOException {
        ColumnPositionMappingStrategy<CsvCandidato> estrategia = new ColumnPositionMappingStrategy<>();
        estrategia.setType(CsvCandidato.class);
        Reader reader = Files.newBufferedReader(path);

        CsvToBean<CsvCandidato> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvCandidato.class)
                .build();
        List<CsvCandidato> csvCandidatos = csvToBean.parse();
        reader.close();

        return csvCandidatos;
    }
    public List<CsvCartaoResposta> readCsvCartaoResposta(Path path) throws IOException {
        ColumnPositionMappingStrategy<CsvCartaoResposta> estrategia = new ColumnPositionMappingStrategy<>();
        estrategia.setType(CsvCartaoResposta.class);
        Reader reader = Files.newBufferedReader(path);

        CsvToBean<CsvCartaoResposta> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvCartaoResposta.class)
                .build();
        List<CsvCartaoResposta> csvCartaoRespostas = csvToBean.parse();
        reader.close();

        return csvCartaoRespostas;
    }
    public void writeCsvFromBean(List<LogCartaoResposta> log) throws Exception {
        Writer writer = Files.newBufferedWriter(Paths.get("/opt/pessoas.csv"));
         StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        sbc.write(log);
        writer.flush();
        writer.close();
    }

}
