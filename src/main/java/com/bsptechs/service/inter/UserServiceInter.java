package com.bsptechs.service.inter;

import com.bsptechs.entities.User;

import java.util.ArrayList;

public interface UserServiceInter {

    User findUserByEmailAndPassword(String email, String password);

    User save(User entity);

    User findById(Integer integer);

    void deleteById(Integer integer);

    ArrayList<User> findAll();

}
