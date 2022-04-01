package com.dominios.vestib.service;

import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.repository.RepositorioDisciplina;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoDisciplina {
    private final RepositorioDisciplina repositorioDisciplina;

    public ServicoDisciplina(RepositorioDisciplina repositorioDisciplina) {
        this.repositorioDisciplina = repositorioDisciplina;
    }
    public List<Disciplina> getByCurso(Long idCurso){
        return repositorioDisciplina.findByIdCurso(idCurso);
    }
}
