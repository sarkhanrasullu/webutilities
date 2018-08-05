package com.bsptechs.service.impl;

import com.bsptechs.dao.inter.FormDataDao;
import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.entities.FormData;
import com.bsptechs.service.inter.FormDataServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FormDataImpl implements FormDataServiceInter {

    @Autowired
    private FormDataDao formDataDao;

    @Override
    public FormData save(FormData entity) {
        return formDataDao.save(entity);
    }

    @Override
    public FormData findById(Integer integer) {
        return formDataDao.findById(integer).get();
    }

    @Override
    public void deleteById(Integer integer) {
        formDataDao.deleteById(integer);
    }

    @Override
    public List<FormData> findAll() {
        return formDataDao.findAll();
    }

    @Override
    public List<FormData> findAllByFormColumnId(FormColumn formId) {
        return formDataDao.findAllByFormColumnId(formId);
    }

    @Override
    public List<FormData> findAllByFormId(Form formId){
        return formDataDao.findAllByFormId(formId);
    }
}
