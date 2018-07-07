package com.bsptechs.service.impl;

import com.bsptechs.dao.inter.FormColumnDao;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.service.inter.FormColumnServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class FormColumnImpl implements FormColumnServiceInter {

    @Autowired
    private FormColumnDao formColumnDao;

    @Override
    public FormColumn save(FormColumn entity) {
        return formColumnDao.save(entity);
    }

    @Override
    public FormColumn findById(Integer integer) {
        return formColumnDao.findById(integer).get();
    }

    @Override
    public void deleteById(Integer integer) {
        formColumnDao.deleteById(integer);
    }

    @Override
    public ArrayList<FormColumn> findAll() {
        return null;
    }
}
