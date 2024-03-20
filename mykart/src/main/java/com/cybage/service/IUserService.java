package com.cybage.service;

import com.cybage.model.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserService {

    public User addUser(@RequestBody User user) throws Exception;

    public User addAdmin(User admin);
}
