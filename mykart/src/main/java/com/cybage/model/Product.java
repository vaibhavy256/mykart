package com.cybage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private int productId;

    private String description;

    private byte[] image;

    private double price;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
