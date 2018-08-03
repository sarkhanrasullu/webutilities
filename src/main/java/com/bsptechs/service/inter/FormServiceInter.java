package com.bsptechs.service.inter;

import com.bsptechs.entities.Form;

import java.util.List;


public interface FormServiceInter {


    Form save(Form entity);

    Form findById(Integer integer);

    void deleteById(Integer integer);

    List<Form> findAll();


}
