package com.cybage.service;


import com.cybage.model.User;
import com.cybage.model.UserType;
import com.cybage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User users = this.userRepo.findByEmail(username);
//        if (users == null) {
//            throw new UsernameNotFoundException(username + "not found.");
//        }
//        return users;

        User users = this.userRepo.findByEmail(username);
        if (users == null) {
            throw new UsernameNotFoundException("no user");
        }
        String usersPassword = users.getPassword();
        List<UserType> usersRoles = users.getUserType();
        return new org.springframework.security.core.userdetails.User(username, usersPassword, usersRoles
                .stream().map(role -> new SimpleGrantedAuthority(usersRoles.toString()))
                .collect(Collectors.toList()));
    }


//    public UserDetails loadUserByUsername(String email,String password) throws UsernameNotFoundException {
//        User getUsers=this.userRepo.findByEmailAndPassword(email,password);
//        if(getUsers==null){
//            throw new UsernameNotFoundException(email+ "not found.");
//        }
//        return getUsers;
//    }


    @Autowired
    public CustomUserDetailsService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
