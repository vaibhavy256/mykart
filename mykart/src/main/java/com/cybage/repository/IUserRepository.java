package com.cybage.repository;

import com.cybage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {

    public boolean existsByEmail(String email);

    //public User findByEmail(String userName);

   public User findByUsername(String userName);

    User findByEmail(String email);
}
