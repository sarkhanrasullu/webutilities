package com.bsptechs.dao.inter;

import com.bsptechs.entities.FormColumn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormColumnDao extends CrudRepository<FormColumn, Integer> {

    @Override
    List<FormColumn> findAll();
}
