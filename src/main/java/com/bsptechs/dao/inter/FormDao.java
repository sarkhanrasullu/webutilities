package com.bsptechs.dao.inter;

import com.bsptechs.entities.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDao extends CrudRepository<Form, Integer> {
}
