package com.cybage.repository;

import com.cybage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {

    public boolean existsByEmail(String email);

}
