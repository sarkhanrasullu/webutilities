package com.bsptechs.service.inter;

import com.bsptechs.entities.FormData;

import java.util.ArrayList;

public interface FormDataServiceInter {


    FormData save(FormData entity);

    FormData findById(Integer integer);

    void deleteById(Integer integer);

    ArrayList<FormData> findAll();
}
