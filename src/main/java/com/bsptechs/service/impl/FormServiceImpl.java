package com.bsptechs.service.impl;

import com.bsptechs.dao.inter.FormDao;
import com.bsptechs.entities.Form;
import com.bsptechs.service.inter.FormServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class FormServiceImpl implements FormServiceInter {


    @Autowired
    private FormDao formDao;

    @Override
    public Form save(Form entity) {
        return formDao.save(entity);
    }

    @Override
    public Form findById(Integer integer) {
        return formDao.findById(integer).get();
    }

    @Override
    public void deleteById(Integer integer) {
        formDao.deleteById(integer);
    }

    @Override
    public ArrayList<Form> findAll() {
        return null;
    }
}
