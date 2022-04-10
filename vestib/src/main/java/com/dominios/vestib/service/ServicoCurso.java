
package com.dominios.vestib.service;

import com.dominios.vestib.model.Curso;
import com.dominios.vestib.repository.RepositorioCurso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoCurso {
    private final RepositorioCurso repositorioCurso;
    private final ServicoDisciplina servicoDisciplina;

    public ServicoCurso(RepositorioCurso repositorioCurso, ServicoDisciplina servicoDisciplina) {
        this.repositorioCurso = repositorioCurso;
        this.servicoDisciplina = servicoDisciplina;
    }
    public Long save(Curso curso){
        repositorioCurso.save(curso);
        return curso.getId();
    }
    public Curso getById(long id){
       Optional<Curso> curso = repositorioCurso.findById(id);
        return curso.orElse(null);
    }
    public List<Curso> getAllOrdered() {
        return repositorioCurso.findAllOrderByCodigo();
    }
    public void delete(long id){
        servicoDisciplina.deleteAllByCurso(id);
        repositorioCurso.deleteById(id);
    }
}
