package com.bsptechs.dao.inter;

import com.bsptechs.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsDao extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);
}
