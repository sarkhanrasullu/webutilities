package com.bsptechs.dao.inter;

import com.bsptechs.entities.FormData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormDataDao extends CrudRepository<FormData, Integer> {
    @Override
    List<FormData> findAll();
}
