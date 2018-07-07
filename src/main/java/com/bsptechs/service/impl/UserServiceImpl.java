package com.bsptechs.service.impl;

import com.bsptechs.dao.inter.UserDao;
import com.bsptechs.entities.User;
import com.bsptechs.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {//Service Dao-nu implement elemez axi, Service ServiceInter-i implement etmelidir
    //qalanlarini da bu qaydada et. Murekkeb methodlar lazim olsa ozum elave edecem
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByEmailAndPassword(String email, String password) {//butun methodlari yazma bize lazim olanlari yaz
        return userDao.findUserByEmailAndPassword(email, password);
    }

    @Override
    public User save(User entity) {
        return userDao.save(entity);
    }

    @Override
    public User findById(Integer integer) {
        return userDao.findById(integer).get();
    }

    @Override
    public void deleteById(Integer integer) {
        userDao.deleteById(integer);
    }

    @Override
    public ArrayList<User> findAll() {
        return null;
    }
}
