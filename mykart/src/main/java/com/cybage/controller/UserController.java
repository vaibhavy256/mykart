package com.cybage.controller;

import com.cybage.model.User;
import com.cybage.model.UserType;
import com.cybage.service.IUserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User>signUp(@RequestBody User user) throws Exception {
        User newUser=userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostConstruct
    public void addAdminAtStartup() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Rushi Kadam");
        user.setContactNo("9665208103");
        user.setEmail("rushibhau@gmail.com");
        user.setPassword("123");
        user.setUserType(UserType.ADMIN);
        userService.addAdmin(user);
    }
}