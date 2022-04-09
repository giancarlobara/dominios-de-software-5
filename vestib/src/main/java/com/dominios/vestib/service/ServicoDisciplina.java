package com.dominios.vestib.service;

import com.dominios.vestib.model.Disciplina;
import com.dominios.vestib.repository.RepositorioDisciplina;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoDisciplina {
    private final RepositorioDisciplina repositorioDisciplina;

    public ServicoDisciplina(RepositorioDisciplina repositorioDisciplina) {
        this.repositorioDisciplina = repositorioDisciplina;
    }
    public Long save(Disciplina disciplina){
        Optional<Disciplina> optionalDisciplina = repositorioDisciplina.findByNomeAndCursoId(disciplina.getNome(),disciplina.getCurso().getId());
        optionalDisciplina.ifPresent(disciplina1 -> {
            disciplina.setId(disciplina1.getId());
        });
        repositorioDisciplina.save(disciplina);
        return disciplina.getId();
    }
    public List<Disciplina> getAll(){
        return repositorioDisciplina.findAll();
    }
    public List<Disciplina> getByCurso(Long idCurso){
        return repositorioDisciplina.findByIdCurso(idCurso);
    }
    public void deleteAllByCurso(long idCurso){
        repositorioDisciplina.deleteAllByCursoId(idCurso);;
    }
    public void delete(Long id){
        repositorioDisciplina.deleteById(id);
    }

}
