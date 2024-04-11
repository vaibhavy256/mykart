package com.cybage.repository;

import com.cybage.model.Product;
import com.cybage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {

    public boolean existsByEmail(String email);

    //public User findByEmail(String userName);

   public User findByUsername(String userName);

    User findByEmail(String email);

    public User findByEmailAndPassword(String email, String password);

    @Query(value="select  u.username,u.email,u.contact_no,p.description,p.image_link,p.price" +
            "c.category_type from user u inner join product p on u.user_id=p.seller_id" +
            "inner join category c  on c.category_id=p.category_id", nativeQuery = true)
    List<Product> FindSellersProductHistory();
}
