package com.bsptechs.service.inter;

import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.entities.FormData;

import java.util.ArrayList;
import java.util.List;

public interface FormDataServiceInter {


    FormData save(FormData entity);

    FormData findById(Integer integer);

    void deleteFormDataByUId(String uId);

    List<FormData> findAll();

    List<FormData> findAllByFormColumnId(FormColumn formColumnId);

    List<FormData> findAllByFormId(Form formId);



}
