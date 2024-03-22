package com.cybage.dto;

import com.cybage.model.Category;
import com.cybage.model.Product;
import com.cybage.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productDTO {
    private int productId;

    private String description;

    private String image;

    private double price;

    private int categoryId;

    private int sellerId;

    public static Product toEntity(productDTO dto) {
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static productDTO toDTO(Product entity) {
        productDTO dto = new productDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}

