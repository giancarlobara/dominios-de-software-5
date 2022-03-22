package com.dominios.vestib.repository;

import com.dominios.vestib.model.AreaEnsino;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioAreaEnsino extends CrudRepository<AreaEnsino, Long> {

    @Query("select a from AreaEnsino a order by a.codigo")
    List<AreaEnsino> findAllOrderByCodigo();
}
