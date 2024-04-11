package com.cybage.service;

import com.cybage.model.Product;
import com.cybage.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    public User addUser(@RequestBody User user) throws Exception;

    public User addAdmin(User admin);

    public List<Product> findAllSellersHistory();
}
