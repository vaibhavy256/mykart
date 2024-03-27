package com.cybage.service;


import com.cybage.model.User;
import com.cybage.model.UserType;
import com.cybage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users=this.userRepo.findByEmail(username);
        if(users==null){
            throw new UsernameNotFoundException(username+ "not found.");
        }
        return users;


    }

    @Autowired
    public CustomUserDetailsService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
