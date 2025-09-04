package com.neoapp.clienteapi.neoapp.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        if("admin".equals(username)) {
            return new User("admin", "{noop}admin123", Collections.emptyList());
        }
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }



}
