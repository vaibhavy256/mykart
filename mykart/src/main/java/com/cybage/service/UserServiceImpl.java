package com.cybage.service;

import com.cybage.model.Product;
import com.cybage.model.User;
import com.cybage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) throws Exception {
        boolean findUser = userRepository.existsByEmail(user.getEmail());
        if (findUser) {
            throw new Exception("User already exists");
        } else {
            System.out.println(user.getPassword());
            String rawPassword = user.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encPassword);
            User newUser = userRepository.save(user);
            return newUser;
        }
    }

    public User updateProfile(User user) {
        User updateUser = userRepository.findByEmail(user.getEmail());
        if (updateUser != null) {
            updateUser.setUsername(user.getUsername());
            updateUser.setContactNo(user.getContactNo());
            updateUser.setEmail(user.getEmail());
            return userRepository.save(updateUser);

        } else
            throw new RuntimeException("Userid not found");

    }

    public List<Product> findAllSellersHistory() {
        return userRepository.FindSellersProductHistory();

    }

    public User addAdmin(User admin) {
        return userRepository.save(admin);
    }
}
