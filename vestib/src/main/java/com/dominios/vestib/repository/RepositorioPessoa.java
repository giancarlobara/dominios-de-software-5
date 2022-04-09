package com.dominios.vestib.repository;

import com.dominios.vestib.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioPessoa extends CrudRepository<Pessoa, Long>{

    Optional<Pessoa> findByCpf(String cpf);
    List<Pessoa> findAll();
}
