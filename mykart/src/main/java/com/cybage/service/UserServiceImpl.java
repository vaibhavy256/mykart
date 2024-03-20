package com.cybage.service;

import com.cybage.model.User;
import com.cybage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    public User addUser( User user) throws Exception {
        boolean findUser=userRepository.existsByEmail(user.getEmail());
        if(findUser){
            throw new Exception("User already exists");
        }
        else{
            User newUser=userRepository.save(user);
            return newUser;
        }

    }

    public User addAdmin(User admin) {
        return userRepository.save(admin);
    }

}
