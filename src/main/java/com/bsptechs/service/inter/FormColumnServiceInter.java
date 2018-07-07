package com.bsptechs.service.inter;

import com.bsptechs.entities.FormColumn;
import java.util.ArrayList;

public interface FormColumnServiceInter {

    FormColumn save(FormColumn entity);

    FormColumn findById(Integer integer);

    void deleteById(Integer integer);

    ArrayList<FormColumn> findAll();
}
