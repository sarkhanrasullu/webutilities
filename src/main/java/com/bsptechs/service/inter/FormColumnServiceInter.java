package com.bsptechs.service.inter;


import com.bsptechs.entities.FormColumn;
import java.util.List;

public interface FormColumnServiceInter {

    FormColumn save(FormColumn entity);

    FormColumn findById(Integer integer);

    void deleteById(Integer integer);

    List<FormColumn> findAll();


}
