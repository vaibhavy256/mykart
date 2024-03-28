package com.cybage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String description;

    private String imageLink;

    private double price;

    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
