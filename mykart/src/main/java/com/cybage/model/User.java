package com.cybage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int userId;

    private String userName;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;

    @Pattern(regexp = "^[0-9]{10}$")
    private String contactNo;

    @Enumerated(EnumType.STRING)
    private UserType userType;


    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId", referencedColumnName = "userId")
    private List<Product> products;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<Address> addresses;
}
