package com.bsptechs.service.impl;

import com.bsptechs.dao.inter.UserDetailsDao;
import com.bsptechs.entities.Authorities;
import com.bsptechs.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    public static String[] getAuthorityArr(List<Authorities> authorities) {

        String[] authoritiesArr = new String[authorities.size()];
        Iterator<Authorities> iter = authorities.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Authorities a = iter.next();
            authoritiesArr[i] = a.getAuthority();
            i++;
        }

        return authoritiesArr;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDetailsDao.findUserByEmail(email);
        UserBuilder builder = null;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(email);

            builder.disabled(!(user.getEnabled() != false));
            builder.password(user.getPassword());

            String[] authoritiesArr = getAuthorityArr(user.getAuthoritiesList());
            builder.authorities(authoritiesArr);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}