package com.bsptechs.dao.inter;

import com.bsptechs.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findUserByEmailAndPassword(String email, String password);

}
